#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdint.h>
#include <string.h>
#include <math.h>
#include "sdk_config.h"
#include "nordic_common.h"
#include "nrf.h"
#include "nrf_drv_twi.h"
#include "nrf_gpio.h"
#include "nrf_gpiote.h"
#include "nrf_drv_gpiote.h"
#include "nrf_delay.h"
#include "app_timer.h"
#include "app_util_platform.h"
#include "accel.h"
#include "log.h"
#include "utils.h"
#include <inttypes.h>

#include "sdk_errors.h"
#include "nrf_twi_mngr.h"


#define TAG "accel"

#define TWI_INSTANCE_ID             1
#define MAX_PENDING_TRANSACTIONS    33


#define ACCEL_INT_PIN		25   //P0.25
#define I2C_SCL_PIN         28   //P0.28
#define I2C_SDA_PIN         29   //P0.29

NRF_TWI_MNGR_DEF(m_nrf_twi_mngr, MAX_PENDING_TRANSACTIONS, TWI_INSTANCE_ID);
NRF_TWI_SENSOR_DEF(m_nrf_twi_sensor, &m_nrf_twi_mngr, MAX_PENDING_TRANSACTIONS);
LIS2DH12_INSTANCE_DEF(m_lis2dh12, &m_nrf_twi_sensor, LIS2DH12_BASE_ADDRESS_HIGH);

static uint8_t                      m_reg_value;
static df_accel_info_t              m_sample;
static app_sched_event_handler_t    m_event_handler = NULL;

static void accel_print_identity(ret_code_t result, void * p_register_data)
{
    LOGI(TAG,"identity: %02X\n", *((uint8_t*)p_register_data));
}

static void accel_get_accel_data_cb(ret_code_t result, lis2dh12_data_t * p_data)
{
    LOGT(TAG,"accel_cb()\n");

    m_sample.ts = (uint16_t)utils_get_tick_time();
    if(m_event_handler != NULL)
        app_sched_event_put(&m_sample, sizeof(df_accel_info_t), m_event_handler);
}

static void gpiote_event_handler(nrf_drv_gpiote_pin_t pin, nrf_gpiote_polarity_t action)
{
    if(pin == ACCEL_INT_PIN)
    {
        LOGT(TAG,"acc_irq()\n");

        lis2dh12_data_read(&m_lis2dh12, accel_get_accel_data_cb, m_sample.values, ACCEL_FIFO_BURST_SIZE);
    }
}

static void gpiote_init()
{
    ret_code_t err_code;

    if (!nrf_drv_gpiote_is_init())
    {
        err_code = nrf_drv_gpiote_init();
        APP_ERROR_CHECK(err_code);
    }

    nrf_drv_gpiote_in_config_t in_config = GPIOTE_CONFIG_IN_SENSE_HITOLO(true);
    in_config.pull = NRF_GPIO_PIN_PULLUP;
    err_code = nrf_drv_gpiote_in_init(ACCEL_INT_PIN, &in_config, gpiote_event_handler);
    APP_ERROR_CHECK(err_code);

    nrf_drv_gpiote_in_event_enable(ACCEL_INT_PIN, true);
}


void accel_init(app_sched_event_handler_t handler)
{
    m_event_handler = handler;

    ret_code_t err_code;
    const nrf_drv_twi_config_t twi_config =
    {
        .scl						= I2C_SCL_PIN,
        .sda						= I2C_SDA_PIN,
        .frequency				= NRF_TWI_FREQ_400K,
        .interrupt_priority	= APP_IRQ_PRIORITY_HIGH
    };

    err_code = nrf_twi_mngr_init(&m_nrf_twi_mngr, &twi_config);
    APP_ERROR_CHECK(err_code);

    err_code = nrf_twi_sensor_init(&m_nrf_twi_sensor);
    APP_ERROR_CHECK(err_code);

    err_code = lis2dh12_init(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);


    lis2dh12_who_am_i_read(&m_lis2dh12, accel_print_identity, &m_reg_value);

/**
 * @brief Macro for setting data acquisition configuration.
 *
 * @param[in] _s        Sensor instance.
 * @param[in] _odr      Data rate. @ref lis2dh12_odr_t
 * @param[in] _lp       Power mode. True if low power mode is enabled.
 * @param[in] _z_en     Enable measure in z-axis. True if enabled.
 * @param[in] _y_en     Enable measure in y-axis. True if enabled.
 * @param[in] _x_en     Enable measure in x-axis. True if enabled.
 * @param[in] _scale    Measurement scale. @ref lis2dh12_scale_t
 * @param[in] _high_res High resolution mode. True if enabled.
 *                      Low power can't be enabled when in high resolution mode.
 */

    // data acquisition configuration: 10HZ sample rate, low power mode, x axis, y axis, z axis, values in range -2g <-> +2g, disable high resolution mode (in this case step size is 256)
    LIS2DH12_DATA_CFG(m_lis2dh12, LIS2DH12_ODR_POWERDOWN, false, true, true, true, LIS2DH12_SCALE_4G, false);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);


/**
 * @brief Macro for setting FIFO configuration.
 *
 * @param[in] _s     Sensor instance.
 * @param[in] _en    Enables FIFO. True if enabled. False clears FIFO setting.
 * @param[in] _mode  FIFO mode. @ref lis2dh12_fifo_mode_t
 * @param[in] _t_sel Trigger event pin selection. True if int2 pin, false if int1 pin.
 * @param[in] _t_thr Trigger threshold.
 */

/*
    LIS2DH12_FIFO_CFG(m_lis2dh12, 1, LIS2DH12_BYPASS, 0, LIS2DH12_MIN_QUEUE_SIZE);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);
*/

    LIS2DH12_FIFO_CFG(m_lis2dh12, true, LIS2DH12_STREAM, false, ACCEL_FIFO_BURST_SIZE - 1);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);


    /**
     * @brief Function for setting filter configuration.
     *
     * @param[in] _s     Sensor instance.
     * @param[in] _mode  Filter mode. @ref lis2dh12_filter_mode_t
     * @param[in] _freq  Filter frequency. @ref lis2dh12_filter_freq_t
     * @param[in] _d_en  Enable filter for data acquisition.
     * @param[in] _c_en  Enable filter for click interrupt.
     * @param[in] _i1_en Enable filter for interrupt 1 aoi.
     * @param[in] _i2_en Enable filter for interrupt 2 aoi.
     */
    LIS2DH12_FILTER_CFG(m_lis2dh12, LIS2DH12_FILTER_MODE_AUTO_RESET, LIS2DH12_FILTER_FREQ_4, false, false, false, false);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);


    /**
     * @brief Macro for configuring INT1 pin.
     *
     * @param[in] _s     Sensor instance.
     * @param[in] _cl    Enable CLICK interrupt on pin.
     * @param[in] _ia1   Enable IA1 interrupt on pin.
     * @param[in] _ia2   Enable IA2 interrupt on pin.
     * @param[in] _zyxda Enable ZYXDA interrupt on pin.
     * @param[in] _wtm   Enable FIFO watermark interrupt on pin.
     * @param[in] _ovr   Enable FIFO overrun interrupt on pin.
     * @param[in] _pol   Pin active state. Affects also int2 pin.
     * @arg       true   Pin is active low.
     * @arg       false  Pin is active high
     * @param[in] _d4d   Enable 4D detection on INT1 pin when 6D is enabled on interrupt 1.
     */

    LIS2DH12_INT1_PIN_CFG(m_lis2dh12, 0, 0, 0, 0, 1, 0, 1, 0);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);

    nrf_delay_us(1000);

    nrf_gpio_cfg_input(ACCEL_INT_PIN, NRF_GPIO_PIN_PULLUP);
    gpiote_init();
}


void accel_state(lis2dh12_odr_t odr, bool hpf_enabled)
{
    ret_code_t err_code;

	LOGI(TAG, "change status (%s, odr: %d)\n", (hpf_enabled?"hpf":"no_hpf"), odr);

	LIS2DH12_DATA_CFG(m_lis2dh12, odr, false, true, true, true, LIS2DH12_SCALE_4G, false);
    err_code = lis2dh12_cfg_commit(&m_lis2dh12);
    APP_ERROR_CHECK(err_code);

	LIS2DH12_FILTER_CFG(m_lis2dh12, LIS2DH12_FILTER_MODE_NORMAL, LIS2DH12_FILTER_FREQ_1, hpf_enabled, false, false, false);
	err_code = lis2dh12_cfg_commit(&m_lis2dh12);
	APP_ERROR_CHECK(err_code);

	if(odr == LIS2DH12_ODR_POWERDOWN)
	{
		utils_release_tick_timer();
	}
	else
	{
		utils_use_tick_timer();
	}

}

