#include <math.h>
#include <stdint.h>
#include "nrf_drv_gpiote.h"
#include "dwm_utils.h"
#include "log.h"
#include "decadriver/deca_param_types.h"
#include "utils.h"

#define TAG "dwm_utils"

#define TX_RX_BUF_LENGTH			1024
#define POLL_TX_TO_RESP_RX_DLY_UUS	140

#if (SPI0_ENABLED == 1)
static const nrf_drv_spi_t m_spi_master_0 = NRF_DRV_SPI_INSTANCE(0);
#endif

static dwt_config_t dwt_config = {
		4,               // Channel number.
		DWT_PRF_64M,     // Pulse repetition frequency.
		DWT_PLEN_1024,    // Preamble length.
		DWT_PAC32,        // Preamble acquisition chunk size. Used in RX only.
		18,               // TX preamble code. Used in TX only.
		18,               // RX preamble code. Used in RX only.
		1,               // Use non-standard SFD (Boolean)
		DWT_BR_850K,      // Data rate.
		DWT_PHRMODE_EXT, // PHY header mode.
		(1024 + 1 + 64 - 64)    // SFD timeout (preamble length + 1 + SFD length - PAC size). Used in RX only.
};


static inline uint16_t min(uint16_t a, uint16_t b)
{
	return (a<b)?(a):(b);
}


static uint8_t m_rx_data[TX_RX_BUF_LENGTH] = {0};

dwm1000_ts_t dwm1000_get_system_time_u64(void)
{
    dwm1000_ts_t ts = { 0 };
    dwt_readsystime(ts.ts_bytes);
    return ts;
}

dwm1000_ts_t dwm1000_get_rx_timestamp_u64(void)
{
    dwm1000_ts_t ts = { 0 };
    dwt_readrxtimestamp(ts.ts_bytes);
    return ts;
}


static uint32_t spi_master_init(void)
{
    nrf_drv_spi_config_t config =
    {
            .ss_pin       = NRF_DRV_SPI_PIN_NOT_USED,
            .irq_priority = APP_IRQ_PRIORITY_LOW,
            .orc          = 0xCC,
            .frequency    = NRF_DRV_SPI_FREQ_1M,
            .mode         = NRF_DRV_SPI_MODE_0,
            .bit_order    = NRF_DRV_SPI_BIT_ORDER_MSB_FIRST,
    };
    config.sck_pin  = DW1000_SCK_PIN;
    config.mosi_pin = DW1000_MOSI_PIN;
    config.miso_pin = DW1000_MISO_PIN;


    return nrf_drv_spi_init(&m_spi_master_0, &config, NULL, NULL);
}

static void init_dw1000_ios(void)
{
    uint32_t err_code = spi_master_init();
    APP_ERROR_CHECK(err_code);

    nrf_gpio_cfg_output(DW1000_SS_PIN);
    nrf_gpio_pin_set(DW1000_SS_PIN);

    nrf_gpio_cfg_input(DW1000_RST, NRF_GPIO_PIN_NOPULL);

    nrf_gpio_cfg_input(DW1000_IRQ, NRF_GPIO_PIN_PULLDOWN);
}

static void deinit_dw1000_ios(void)
{

	nrf_drv_spi_uninit(&m_spi_master_0);
	nrf_gpio_cfg_input(DW1000_SS_PIN,NRF_GPIO_PIN_NOPULL);
	nrf_gpio_cfg_input(DW1000_SCK_PIN, NRF_GPIO_PIN_NOPULL);
	nrf_gpio_cfg_input(DW1000_MISO_PIN, NRF_GPIO_PIN_NOPULL);
	nrf_gpio_cfg_input(DW1000_MOSI_PIN, NRF_GPIO_PIN_NOPULL);
	NRF_SPI0->ENABLE = 0;
}


static void reset_dw1000(void)
{
    nrf_gpio_cfg_output(DW1000_RST);
    nrf_gpio_pin_clear(DW1000_RST);
    nrf_delay_ms(2);
    nrf_gpio_cfg_input(DW1000_RST, NRF_GPIO_PIN_NOPULL);
}


static void spi_reinit(bool fast)
{
    nrf_drv_spi_uninit(&m_spi_master_0);

    nrf_drv_spi_config_t config =
    {
            .ss_pin       = NRF_DRV_SPI_PIN_NOT_USED,
            .irq_priority = APP_IRQ_PRIORITY_LOW,
            .orc          = 0xCC,
			.frequency    = (fast)?NRF_DRV_SPI_FREQ_8M:NRF_DRV_SPI_FREQ_1M,
            .mode         = NRF_DRV_SPI_MODE_0,
            .bit_order    = NRF_DRV_SPI_BIT_ORDER_MSB_FIRST,
    };
    config.sck_pin  = DW1000_SCK_PIN;
	config.mosi_pin = DW1000_MOSI_PIN;
	config.miso_pin = DW1000_MISO_PIN;

    nrf_drv_spi_init(&m_spi_master_0, &config, NULL, NULL);
}

static int deca_wakeup()
{
	spi_reinit(false);
	uint8_t tmp[600];
	int result = dwt_spicswakeup(tmp, 600);
	spi_reinit(true);

	return result;
}

void deca_sleep(unsigned int time_ms)
{
	nrf_delay_ms(time_ms);
}

decaIrqStatus_t decamutexon(void)
{
    uint8_t temp = 0;
    app_util_critical_region_enter(&temp);

    return temp;
}

void decamutexoff(decaIrqStatus_t s)
{
    app_util_critical_region_exit(s);
}


#define MAX_SPI_CHUNK_SIZE  0xFF

int writetospi(uint16 headerLength, const uint8 *headerBuffer, uint32 bodylength, const uint8 *bodyBuffer)
{
    uint8_t irqs = decamutexon();
    nrf_gpio_pin_clear(DW1000_SS_PIN);

    uint32_t err_code = nrf_drv_spi_transfer(&m_spi_master_0, (uint8 *)headerBuffer, headerLength, m_rx_data, 0);
    if(err_code != NRF_SUCCESS)
    {
		LOGI(TAG,"Write error - header (SPI).\n\r");
    }

    int remain = bodylength;

    while(remain > 0)
    {
/*
        char log_str[80];
        sprintf(log_str, "Index: %ld, %d\n", (bodylength-remain), min(remain, MAX_SPI_CHUNK_SIZE));
        SEGGER_RTT_WriteString(0, log_str);
*/
        err_code = nrf_drv_spi_transfer(&m_spi_master_0, (uint8 *)bodyBuffer+(bodylength-remain), min(remain, MAX_SPI_CHUNK_SIZE), m_rx_data, 0);
        if(err_code != NRF_SUCCESS)
        {
			LOGI(TAG,"Write error - body (SPI).\n\r");
        }

        remain -= min(remain, MAX_SPI_CHUNK_SIZE);
    }

    nrf_gpio_pin_set(DW1000_SS_PIN);
    decamutexoff(irqs);

    return 0;
}

int readfromspi(uint16 headerLength,  const uint8 *headerBuffer, uint32 readlength, uint8 *readBuffer)
{
    uint8_t irqs = decamutexon();
    nrf_gpio_pin_clear(DW1000_SS_PIN);

    uint32_t err_code = nrf_drv_spi_transfer(&m_spi_master_0, (uint8 *)headerBuffer, headerLength, m_rx_data, headerLength);

    if(err_code != NRF_SUCCESS)
    {
		LOGI(TAG,"Write error - read header (SPI).\n\r");
    }

    int remain = readlength;

    while(remain > 0)
    {
/*
        char log_str[80];
        sprintf(log_str, "Index: %ld, %d\n", (readlength-remain), min(remain, MAX_SPI_CHUNK_SIZE));
        SEGGER_RTT_WriteString(0, log_str);
*/
        err_code = nrf_drv_spi_transfer(&m_spi_master_0, m_rx_data, min(remain, MAX_SPI_CHUNK_SIZE), readBuffer+(readlength-remain), min(remain, MAX_SPI_CHUNK_SIZE));
        if(err_code != NRF_SUCCESS)
        {
			LOGI(TAG,"Read error (SPI).\n\r");
        }

        remain -= min(remain, MAX_SPI_CHUNK_SIZE);
    }

    nrf_gpio_pin_set(DW1000_SS_PIN);
    decamutexoff(irqs);

    return 0;
}

int dwm1000_phy_init()
{
	init_dw1000_ios();
    deca_wakeup();
	reset_dw1000();

	spi_reinit(false);
    int init_result = dwt_initialise(DWT_LOADUCODE);
	spi_reinit(true);

	dwt_configure(&dwt_config);

	uint32 devid = dwt_readdevid();
	LOGI(TAG,"Decawave magic is %08lX\n",devid);

    if(init_result == DWT_ERROR)
    {
		LOGI(TAG,"Error on dwt_initialise!\n");
        return 1;
	}

	dwt_setleds(3);

    dwt_setrxantennadelay(0);
    dwt_settxantennadelay(0);

    dwt_setrxaftertxdelay(POLL_TX_TO_RESP_RX_DLY_UUS);
    dwt_setrxtimeout(0);

    //    dwt_write32bitreg(SYS_CFG_ID, SYS_CFG_DIS_DRXB | SYS_CFG_RXWTOE);

    dwt_setsmarttxpower(0);

    dwt_txconfig_t configTx;
    configTx.PGdly = 0x95;
    configTx.power = 0x5F5F5F5F;

    //configTx.PGdly = 0xC5;
    //configTx.power = 0x0E080222;

    //configTx.power = 0x9A9A9A9A;
    //configTx.power = 0x1F1F1F1F;

    dwt_configuretxrf(&configTx);

   /* float time = dwt_estimate_tx_time(config_long, sizeof(rtls_tdoa_poll_msg_t), false);
	LOGI(TAG,"poll length: %d byte, %f sec\n", sizeof(rtls_tdoa_poll_msg_t), time);

    float time2 = dwt_estimate_tx_time(config_long, sizeof(rtls_tdoa_aggr_msg_t), false);
	LOGI(TAG,"aggr length: %d byte, %f sec\n", sizeof(rtls_tdoa_aggr_msg_t), time2);*/


    int sys_cfg = dwt_read32bitreg(SYS_CFG_ID);
	LOGI(TAG,"sysconfig: %08X\n", sys_cfg);

    dwt_setinterrupt(DWT_INT_TFRS | DWT_INT_RFCG | DWT_INT_ARFE | DWT_INT_RFSL | DWT_INT_SFDT | DWT_INT_RPHE | DWT_INT_RFCE | DWT_INT_RFTO | DWT_INT_RXPTO | DWT_INT_RXOVRR, 1);

    int sys_mask = dwt_read32bitreg(SYS_MASK_ID);
	LOGI(TAG,"sysmask: %08X\n", sys_mask);

    uint8_t pgdelay = dwt_read8bitoffsetreg(TX_CAL_ID, TC_PGDELAY_OFFSET);
	LOGI(TAG,"pgdelay: %02X\n", pgdelay & 0xFF);

    uint32_t txpower = dwt_read32bitreg(TX_POWER_ID);
	LOGI(TAG,"txpower: %08lX\n", txpower);

    return 0;
}

static void gpiote_event_handler(nrf_drv_gpiote_pin_t pin, nrf_gpiote_polarity_t action)
{
    if(pin == DW1000_IRQ)
    {
        dwt_isr();
    }
}

void dwm1000_irq_enable()
{
    ret_code_t err_code;

    if (!nrf_drv_gpiote_is_init())
    {
        err_code = nrf_drv_gpiote_init();
        APP_ERROR_CHECK(err_code);
    }

    nrf_drv_gpiote_in_config_t in_config = GPIOTE_CONFIG_IN_SENSE_LOTOHI(true);
    in_config.pull = NRF_GPIO_PIN_PULLDOWN;
    err_code = nrf_drv_gpiote_in_init(DW1000_IRQ, &in_config, gpiote_event_handler);
    APP_ERROR_CHECK(err_code);

    nrf_drv_gpiote_in_event_enable(DW1000_IRQ, true);
}

void dwm1000_irq_disable()
{
    nrf_drv_gpiote_in_event_disable(DW1000_IRQ);
    nrf_drv_gpiote_in_uninit(DW1000_IRQ);
}

void dwm1000_phy_release()
{
	reset_dw1000();
	deinit_dw1000_ios();
}

