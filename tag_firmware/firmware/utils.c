#include "utils.h"
#include "nrf_gpio.h"
#include "nrf_drv_timer.h"
#include "nrf_log.h"
#include "nrf_log_ctrl.h"
#include "nrfx_rtc.h"
#include "app_util.h"
#include "log.h"

#define TAG "utils"

const static nrf_drv_timer_t m_execution_timer = NRF_DRV_TIMER_INSTANCE(4);
const nrfx_rtc_t m_tick_timer = NRFX_RTC_INSTANCE(2);
static int m_tick_timer_ref_counter = 0;

void utils_init()
{
	uint32_t err_code;
	nrf_drv_timer_config_t timer_cfg = {
		.frequency          = NRF_TIMER_FREQ_16MHz,
		.mode               = NRF_TIMER_MODE_TIMER,
		.bit_width          = NRF_TIMER_BIT_WIDTH_32,
		.interrupt_priority = 0,
		.p_context          = NULL
	};
	err_code = nrf_drv_timer_init(&m_execution_timer, &timer_cfg, NULL);
	APP_ERROR_CHECK(err_code);
}

void utils_start_execution_timer()
{
	nrf_drv_timer_clear(&m_execution_timer);
	nrf_drv_timer_enable(&m_execution_timer);
}

uint32_t utils_stop_execution_timer()
{
	uint32_t counter = nrf_drv_timer_capture(&m_execution_timer, NRF_TIMER_CC_CHANNEL0);
	return counter >> 4;
}

static void rtc_handler(nrfx_rtc_int_type_t int_type)
{

}

void utils_use_tick_timer() {
    ret_code_t err_code;

	if(m_tick_timer_ref_counter == 0)
	{
		nrfx_rtc_config_t config = NRFX_RTC_DEFAULT_CONFIG;
		config.prescaler = 32;      // 1.007 ms
		err_code = nrfx_rtc_init(&m_tick_timer, &config, rtc_handler);
		APP_ERROR_CHECK(err_code);

		nrfx_rtc_enable(&m_tick_timer);

		LOGI(TAG,"tick timer started\n");
	}

	m_tick_timer_ref_counter++;
}

void utils_release_tick_timer() {
	m_tick_timer_ref_counter--;
	if(m_tick_timer_ref_counter<0)
		m_tick_timer_ref_counter = 0;

	if(m_tick_timer_ref_counter == 0)
	{
		nrfx_rtc_disable(&m_tick_timer);
		nrfx_rtc_uninit(&m_tick_timer);

		LOGI(TAG,"tick timer stopped\n");
	}
}

uint32_t utils_get_tick_time() {
    return nrfx_rtc_counter_get(&m_tick_timer);
}

__attribute__((weak))
void my_error_function(int code, const char *filename, const int line)
{
    __disable_irq();

    NRF_LOG_ERROR("FE: 0x%04X (%s:%d)", code, filename, line);
    NRF_LOG_FINAL_FLUSH();

    for(;;) {}
}
