#include "impl_observer.h"
#include "dwm_utils.h"
#include "log.h"

#include "nrf.h"
#include "nrf_gpio.h"
#include "nrf_drv_gpiote.h"
#include "nrf_gpiote.h"

#include "uart.h"
#include "app_error.h"
#include "crc.h"

#define TAG "obs"

#define DW1000_RX_BUFFER_SIZE	512
static uint8_t	m_rx_buffer[DW1000_RX_BUFFER_SIZE];

static void mac_rxok_callback_impl(const dwt_cb_data_t *data)
{
	if(data->datalength - 2 > DW1000_RX_BUFFER_SIZE || data->datalength < 2)
	{
		dwt_rxenable(0);
		return;
	}

	uint16_t data_length = data->datalength - 2;

	dwt_readrxdata(m_rx_buffer, data_length, 0);
    dwm1000_ts_t rxts = dwm1000_get_rx_timestamp_u64();

	crc_t crc = crc_init();
	crc = crc_update(crc, &data_length, sizeof(uint16_t));
	crc = crc_update(crc, &rxts.ts, sizeof(uint64_t));
	crc = crc_update(crc, m_rx_buffer, data_length);
	crc = crc_finalize(crc);

	uart_put((uint8_t*)"x",1);
	uart_put((uint8_t*)&data_length, sizeof(uint16_t));
    uart_put((uint8_t*)&rxts.ts, sizeof(uint64_t));
	uart_put((uint8_t*)m_rx_buffer, data_length);
	uart_put((uint8_t*)&crc, sizeof(crc_t));

	dwt_rxenable(0);
}

static void mac_rxerr_callback_impl(const dwt_cb_data_t *data)
{
	uart_put((uint8_t*)"x",1);
	app_uart_put(0);
	app_uart_put(0);

	dwt_rxenable(0);
}




void impl_observer_init()
{
	LOGI(TAG,"mode: obs\n");

	NRF_CLOCK->EVENTS_HFCLKSTARTED = 0;
	NRF_CLOCK->TASKS_HFCLKSTART = 1;

    dwm1000_irq_enable();
	uart_init();
	//uart_test(true);

	LOGI(TAG,"initialize dw1000 phy\n");
	dwm1000_phy_init();

	dwt_setcallbacks(NULL, mac_rxok_callback_impl, NULL, mac_rxerr_callback_impl);
	dwt_rxenable(0);
}

void impl_observer_loop()
{
	while(1) {}
}
