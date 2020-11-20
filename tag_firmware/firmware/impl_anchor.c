#include "impl_anchor.h"
#include <stdint.h>

#include "dwm_utils.h"
#include "log.h"
#include "utils.h"
#include "address_handler.h"

#include "nrf_gpio.h"
#include "nrf_drv_gpiote.h"
#include "nrf_gpiote.h"

#include "nrf_drv_timer.h"
#include "timing.h"

#define TAG "anchor"

#define SYNC_COMPENSATION_CONST_US	150

typedef enum {
	ANCHOR_STATE__DISCOVERY = 0,
	ANCHOR_STATE__WAITING_NEXT_SF = 1,
	ANCHOR_STATE__BEFORE_ANCHOR_MSG = 2,
	ANCHOR_STATE__SENDING_ANCHOR_MSG = 3,
	ANCHOR_STATE__AFTER_ANCHOR_MSG = 4,
	ANCHOR_STATE__TAG_FRAME = 5
} anchor_state_t;

typedef enum
{
	EVENT_RX = 1,
	EVENT_TIMER = 2,
	EVENT_SF_BEGIN = 3
} event_type_t;

const static nrf_drv_timer_t	m_frame_timer = NRF_DRV_TIMER_INSTANCE(1);
static int32_t					m_frame_timer_compensation_us = 0;
static anchor_state_t			m_anchor_state = ANCHOR_STATE__DISCOVERY;
static uint16_t					m_anchor_id = 1;
static uint8_t					m_superframe_id = 0;
static uint8_t					m_rx_buffer[SF_MAX_MESSAGE_SIZE];
static rx_info_t				m_tag_infos[TIMING_TAG_COUNT];
static rx_info_t				m_anchor_infos[TIMING_ANCHOR_COUNT];

static void frame_timer_event_handler(nrf_timer_event_t event_type, void* p_context);
static void restart_frame_timer();
static void set_frame_timer(uint32_t us);
static void event_handler(event_type_t event_type, const uint8_t* data, uint16_t datalength);


static void set_anchor_state(anchor_state_t newstate)
{
	m_anchor_state = newstate;
}

inline static uint32_t get_slot_time_by_message(dwm1000_ts_t rx_ts)
{
    uint32_t rx_ts_32 = rx_ts.ts >> 8;
    uint32_t systime_32 = dwm1000_get_system_time_u64().ts >> 8;

	return (systime_32-rx_ts_32)/(UUS_TO_DWT_TIME/256) + TIMING_MESSAGE_TX_PREFIX_TIME_US;
}

static void mac_rxok_callback_impl(const dwt_cb_data_t *data)
{
	if(data->datalength - 2 > SF_MAX_MESSAGE_SIZE)
	{
		LOGE(TAG,"oversized msg\n");
		dwt_rxenable(0);
		return;
	}

	dwt_readrxdata(m_rx_buffer, data->datalength - 2, 0);

	event_handler(EVENT_RX, m_rx_buffer, data->datalength - 2);

	dwt_rxenable(0);
}

static void mac_rxerr_callback_impl(const dwt_cb_data_t *data)
{
	LOGI(TAG, "rx err\n");

	dwt_rxenable(0);
}

static void mac_txok_callback_impl(const dwt_cb_data_t* data)
{
	dwt_rxenable(0);
}


static void restart_frame_timer() {
	m_frame_timer_compensation_us = 0;

	nrf_drv_timer_pause(&m_frame_timer);
	nrf_drv_timer_clear(&m_frame_timer);
	uint32_t time_ticks = nrf_drv_timer_us_to_ticks(&m_frame_timer, TIMING_SUPERFRAME_LENGTH_US);
	nrf_drv_timer_compare(&m_frame_timer, NRF_TIMER_CC_CHANNEL1, time_ticks, true);
	nrf_drv_timer_resume(&m_frame_timer);
}

static void set_frame_timer(uint32_t us) {
	nrf_drv_timer_pause(&m_frame_timer);
	uint32_t time_ticks = nrf_drv_timer_us_to_ticks(&m_frame_timer, us - m_frame_timer_compensation_us);
	nrf_drv_timer_compare(&m_frame_timer, NRF_TIMER_CC_CHANNEL0, time_ticks, true);
	nrf_drv_timer_resume(&m_frame_timer);
}

static void compensate_frame_timer(uint32_t c)
{
	uint32_t state = nrf_drv_timer_capture(&m_frame_timer, NRF_TIMER_CC_CHANNEL2) >> 4;
	LOGT(TAG,"state: %ld\n", state);

	m_frame_timer_compensation_us = c - state;

	nrf_drv_timer_pause(&m_frame_timer);
	uint32_t time_ticks = nrf_drv_timer_us_to_ticks(&m_frame_timer, TIMING_SUPERFRAME_LENGTH_US - m_frame_timer_compensation_us);
	nrf_drv_timer_compare(&m_frame_timer, NRF_TIMER_CC_CHANNEL1, time_ticks, true);
	nrf_drv_timer_resume(&m_frame_timer);
}

static void transmit_anchor_msg() {
	sf_anchor_msg_t	msg;
	msg.hdr.src_id = m_anchor_id;
	msg.hdr.fctrl = SF_HEADER_FCTRL_MSG_TYPE_ANCHOR_MESSAGE;
	msg.tr_id = m_superframe_id;
	memcpy(msg.tags, m_tag_infos, TIMING_TAG_COUNT * sizeof(rx_info_t));
	memcpy(msg.anchors, m_anchor_infos, TIMING_ANCHOR_COUNT * sizeof(rx_info_t));

    dwm1000_ts_t sys_ts = dwm1000_get_system_time_u64();
    uint32_t tx_ts_32 = (sys_ts.ts + (TIMING_MESSAGE_TX_PREFIX_TIME_US * UUS_TO_DWT_TIME)) >> 8;
	uint64_t tx_ts = (((uint64_t)(tx_ts_32 & 0xFFFFFFFEUL)) << 8);

    dwm1000_ts_u64_to_pu8(tx_ts, msg.tx_ts);

	dwt_forcetrxoff();
	dwt_writetxdata(sizeof(sf_anchor_msg_t) + 2, (uint8_t*)&msg, 0);
	dwt_writetxfctrl(sizeof(sf_anchor_msg_t) + 2, 0, false);
	dwt_setdelayedtrxtime(tx_ts_32);
	if(dwt_starttx(DWT_START_TX_DELAYED) != DWT_SUCCESS)
	{
		LOGE(TAG, "err: starttx\n");
	}

	memset(m_anchor_infos,0,TIMING_ANCHOR_COUNT * sizeof(rx_info_t));
}

static void event_handler(event_type_t event_type, const uint8_t* data, uint16_t datalength)
{
	LOGT(TAG, "S %d, E %d (%ld)\n", m_anchor_state, event_type, (nrf_drv_timer_capture(&m_frame_timer, NRF_TIMER_CC_CHANNEL2) >> 4));

	if(event_type == EVENT_SF_BEGIN)
	{
		restart_frame_timer();

		LOGT(TAG, "SF\n");
	}

	if(m_anchor_state == ANCHOR_STATE__DISCOVERY)
	{
		if(event_type == EVENT_RX)
		{
			// Found anchor message, sync

			sf_header_t* hdr = (sf_header_t*)data;
			if(hdr->fctrl == SF_HEADER_FCTRL_MSG_TYPE_ANCHOR_MESSAGE)
			{
				set_anchor_state(ANCHOR_STATE__WAITING_NEXT_SF);

				uint32_t slot_time_us = get_slot_time_by_message(dwm1000_get_rx_timestamp_u64());
				uint32_t sf_time_us = hdr->src_id * TIMING_ANCHOR_MESSAGE_LENGTH_US + slot_time_us;

				compensate_frame_timer(sf_time_us);

				LOGT(TAG,"SFT %ld\n", sf_time_us)

				sf_anchor_msg_t* msg = (sf_anchor_msg_t*)data;
				m_superframe_id = msg->tr_id;
			}
		}
		else if(event_type == EVENT_SF_BEGIN)
		{
			// No sync message received, start superframe
			m_superframe_id++;
			if(m_superframe_id > TIMING_DISCOVERY_SUPERFRAME_COUNT)
			{
				m_superframe_id = 0;
				set_anchor_state(ANCHOR_STATE__WAITING_NEXT_SF);
			}
		}
	}
	else if(m_anchor_state == ANCHOR_STATE__WAITING_NEXT_SF ||
			m_anchor_state == ANCHOR_STATE__TAG_FRAME)
	{
		if(event_type == EVENT_SF_BEGIN)
		{
			m_superframe_id++;

			uint32_t delay = m_anchor_id * TIMING_ANCHOR_MESSAGE_LENGTH_US;
			if(delay == 0)
			{
				set_frame_timer(TIMING_ANCHOR_MESSAGE_LENGTH_US);
				transmit_anchor_msg();
				set_anchor_state(ANCHOR_STATE__SENDING_ANCHOR_MSG);
			}
			else
			{
				set_frame_timer(m_anchor_id * TIMING_ANCHOR_MESSAGE_LENGTH_US);
				set_anchor_state(ANCHOR_STATE__BEFORE_ANCHOR_MSG);
				//dwt_rxenable(0);
			}
		}
		else if(event_type == EVENT_RX)
		{
			sf_header_t* hdr = (sf_header_t*)data;
			if(hdr->fctrl == SF_HEADER_FCTRL_MSG_TYPE_TAG_MESSAGE)
			{
				sf_tag_msg_t* msg = (sf_tag_msg_t*)data;
				dwt_readrxtimestamp(m_tag_infos[msg->hdr.src_id].rx_ts);
				LOGI(TAG,"t save\n");
			}
		}
	}
	else if(m_anchor_state == ANCHOR_STATE__BEFORE_ANCHOR_MSG)
	{
		if(event_type == EVENT_TIMER)
		{
			set_frame_timer((m_anchor_id + 1) * TIMING_ANCHOR_MESSAGE_LENGTH_US);
			transmit_anchor_msg();
			set_anchor_state(ANCHOR_STATE__SENDING_ANCHOR_MSG);
		}
		else if(event_type == EVENT_RX)
		{
			// Found anchor message, sync

			sf_header_t* hdr = (sf_header_t*)data;
			if(hdr->fctrl == SF_HEADER_FCTRL_MSG_TYPE_ANCHOR_MESSAGE)
			{
				uint32_t slot_time_us = get_slot_time_by_message(dwm1000_get_rx_timestamp_u64());
				uint32_t sf_time_us = hdr->src_id * TIMING_ANCHOR_MESSAGE_LENGTH_US + slot_time_us;

				compensate_frame_timer(sf_time_us + SYNC_COMPENSATION_CONST_US);
				LOGT(TAG,"SFT %ld\n", sf_time_us);

				dwt_readrxtimestamp(m_anchor_infos[hdr->src_id].rx_ts);
				LOGI(TAG,"a save 1\n");
			}
		}
	}
	else if(m_anchor_state == ANCHOR_STATE__SENDING_ANCHOR_MSG)
	{
		if(m_anchor_id + 1 == TIMING_ANCHOR_COUNT)
		{
			set_anchor_state(ANCHOR_STATE__TAG_FRAME);
			memset(m_tag_infos,0,TIMING_TAG_COUNT * sizeof(rx_info_t));
		}
		else
		{
			set_frame_timer(TIMING_ANCHOR_COUNT * TIMING_ANCHOR_MESSAGE_LENGTH_US);
			set_anchor_state(ANCHOR_STATE__AFTER_ANCHOR_MSG);
			memset(m_tag_infos,0,TIMING_TAG_COUNT * sizeof(rx_info_t));
		}
	}
	else if(m_anchor_state == ANCHOR_STATE__AFTER_ANCHOR_MSG)
	{
		if(event_type == EVENT_TIMER)
		{
			set_anchor_state(ANCHOR_STATE__TAG_FRAME);
		}
		else if(event_type == EVENT_RX)
		{
			sf_header_t* hdr = (sf_header_t*)data;
			if(hdr->fctrl == SF_HEADER_FCTRL_MSG_TYPE_ANCHOR_MESSAGE)
			{
				dwt_readrxtimestamp(m_anchor_infos[hdr->src_id].rx_ts);
				LOGI(TAG,"a save 2\n");
			}
		}
		//dwt_rxenable(0);
	}
}

static void frame_timer_event_handler(nrf_timer_event_t event_type, void* p_context)
{
	utils_start_execution_timer();
	if(event_type == NRF_TIMER_EVENT_COMPARE0)
		event_handler(EVENT_TIMER, NULL, 0);
	else if(event_type == NRF_TIMER_EVENT_COMPARE1)
		event_handler(EVENT_SF_BEGIN, NULL, 0);
}

int impl_anchor_init()
{
	m_anchor_id = addr_handler_get_virtual_addr();
	if(m_anchor_id == 0xFFFF)
	{
		LOGE(TAG, "no address specified\n");
		for(;;);
	}

	if(m_anchor_id >= TIMING_ANCHOR_COUNT)
	{
        ERROR(TAG, "anchor count reached\n");
	}

	LOGI(TAG,"mode: anchor\n");
	LOGI(TAG,"addr: %04X\n", m_anchor_id);
	LOGI(TAG,"sf length: %d\n", TIMING_SUPERFRAME_LENGTH_MS);



	LOGI(TAG,"initialize dw1000 phy\n");
	dwm1000_phy_init();
    dwm1000_irq_enable();

	dwt_setcallbacks(mac_txok_callback_impl, mac_rxok_callback_impl, NULL, mac_rxerr_callback_impl);

	uint32_t err_code;
	nrf_drv_timer_config_t timer_cfg = {
		.frequency          = NRF_TIMER_FREQ_16MHz,
		.mode               = NRF_TIMER_MODE_TIMER,
		.bit_width          = NRF_TIMER_BIT_WIDTH_32,
		.interrupt_priority = 2,
		.p_context          = NULL
	};
	err_code = nrf_drv_timer_init(&m_frame_timer, &timer_cfg, frame_timer_event_handler);
	APP_ERROR_CHECK(err_code);
	nrf_drv_timer_enable(&m_frame_timer);
	nrf_drv_timer_pause(&m_frame_timer);

	restart_frame_timer();

	dwt_rxenable(0);

	return 0;
}




