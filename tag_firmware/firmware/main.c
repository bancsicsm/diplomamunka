/**
 * @file main.c
 * @brief Főfunkciókat tartalmazó fájl
 */
#include <stdint.h>
#include <inttypes.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include "nordic_common.h"
#include "nrf.h"
#include "app_error.h"
#include "nrf_gpio.h"
#include "app_gpiote.h"
#include "nrf_drv_gpiote.h"
#include "nrf_drv_timer.h"
#include "nrf_drv_saadc.h"
#include "nrf_drv_uart.h"
#include "nrf_drv_clock.h"
#include "nrf_delay.h"
#include "app_uart.h"
#include "nrf_drv_twi.h"
#include "SEGGER_RTT.h"
#include "log.h"
#include "utils.h"
#include "boards.h"
#include "app_timer.h"
#include "app_button.h"
#include "app_scheduler.h"
#include "nrf_log.h"
#include "nrf_log_ctrl.h"
#include "nrf_log_default_backends.h"
#include "nrf_pwr_mgmt.h"
#include "nrf_sdh.h"
#include "ble_func.h"
#include "uart.h"
#include "tdoa_as_func_test.h"
#include "address_handler.h"
#include "impl_observer.h"
#include "impl_anchor.h"
#include "impl_tag.h"
#include "timing.h"
#include "accel.h"

#include "hardfault.h"

#define TAG "main"
//  debugging with gdb: ./JLinkGDBServer -if SWD -device nRF51822
//  ./arm-none-eabi-gdb --tui /home/strauss/munka/ble-decawave/nRF51_SDK_10.0.0_dc26b5e/examples/ble-decawave-tag/ble_app_deca/dev_deca_bt/s110/armgcc/_build/nrf51822_xxac_s110_res.out
//	(gdb) target remote localhost:2331
//	(gdb) load /home/strauss/munka/ble-decawave/nRF51_SDK_10.0.0_dc26b5e/examples/ble-decawave-tag/ble_app_deca/dev_deca_bt/s110/armgcc/_build/nrf51822_xxac_s110_res.out
//	(gdb) monitor reset/go/halt
//	(gdb) monitor memU8 <memory_address>

#define LED_HARD_FAULT                  BSP_BOARD_LED_0

#define BUTTON_USER                     BSP_BUTTON_0
#define BUTTON_USER_INDEX               0
#define BUTTON_DETECTION_DELAY          APP_TIMER_TICKS(50)

static void log_init(void)
{
    ret_code_t err_code = NRF_LOG_INIT(NULL);
    APP_ERROR_CHECK(err_code);

    NRF_LOG_DEFAULT_BACKENDS_INIT();
}


static void leds_init(void)
{
    bsp_board_init(BSP_INIT_LEDS);
}

static void timers_init(void)
{
    // Initialize timer module, making it use the scheduler
    ret_code_t err_code = app_timer_init();
    APP_ERROR_CHECK(err_code);
}


static void button_event_handler(uint8_t pin_no, uint8_t button_action)
{
   // ret_code_t err_code;

    switch (pin_no)
    {
        case BUTTON_USER:
            NRF_LOG_INFO("Send button state change");
            /*err_code = ble_lbs_on_button_change(m_conn_handle, &m_lbs, button_action);
            if (err_code != NRF_SUCCESS &&
                err_code != BLE_ERROR_INVALID_CONN_HANDLE &&
                err_code != NRF_ERROR_INVALID_STATE &&
                err_code != BLE_ERROR_GATTS_SYS_ATTR_MISSING)
            {
                APP_ERROR_CHECK(err_code);
            }
            break;*/
        break;
    }
}

static void buttons_init(void)
{
    ret_code_t err_code;

    //The array must be static because a pointer to it will be saved in the button handler module.
    static app_button_cfg_t buttons[] =
    {
        {BUTTON_USER, false, BUTTON_PULL, button_event_handler}
    };

    err_code = app_button_init(buttons, ARRAY_SIZE(buttons),
                               BUTTON_DETECTION_DELAY);
    APP_ERROR_CHECK(err_code);

    err_code = app_button_enable();
    APP_ERROR_CHECK(err_code);
}

static void power_management_init(void)
{
    ret_code_t err_code;
    err_code = nrf_pwr_mgmt_init();
    APP_ERROR_CHECK(err_code);
}


static void idle_state_handle(void)
{
    if (NRF_LOG_PROCESS() == false)
    {
        nrf_pwr_mgmt_run();
    }
}


int main(void)
{
    log_init();
    utils_init();

    LOGI(TAG,"--> start app <--\n");

	nrf_delay_ms(50);


    APP_SCHED_INIT(
                ENSURE_ALIGN(MAX(MAX(
                    sizeof(df_ranging_info_t),
                    sizeof(df_accel_info_t)
                    ),
                    sizeof(df_anchor_ranging_info_t)),sizeof(uint32_t)),
                10);

    leds_init();
    timers_init();
    buttons_init();
    power_management_init();

    if(app_button_is_pushed(BUTTON_USER_INDEX)) {
        impl_observer_init();
    }
    else {
		addr_handler_init();
        if(!addr_handler_is_anchor())
        {
            ret_code_t err_code;
            err_code = nrf_sdh_enable_request();
            ERROR_CHECK(err_code, NRF_SUCCESS);

			sd_clock_hfclk_request();
            ble_func_init(addr_handler_get_device_name());

            impl_tag_init();
        }
        else
        {
			nrf_drv_clock_hfclk_request(NULL);
			do {} while(!nrf_drv_clock_hfclk_is_running());

            impl_anchor_init();
        }
    }


    LOGI(TAG, "exec loop\n");
    for(;;)
    {
        app_sched_execute();
        idle_state_handle();
    }

}

void HardFault_process(HardFault_stack_t * p_stack)
{
    bsp_board_led_on(LED_HARD_FAULT);
    for(;;);
}

