#ifndef DW1000_H_
#define DW1000_H_

#include <stdio.h>
#include <string.h>
#include <inttypes.h>
#include <stdint.h>
#include "math.h"
#include "app_uart.h"
#include "app_error.h"
#include "app_util_platform.h"
#include "nordic_common.h"
#include "nrf.h"
#include "nrf_drv_spi.h"
#include "nrf_soc.h"
#include "nrf_gpio.h"
#include "nrf_delay.h"
#include "nrf_delay.h"
#include "deca_types.h"
#include "deca_device_api.h"
#include "deca_regs.h"
#include "SEGGER_RTT.h"


#define DW1000_RST		24
#define DW1000_IRQ		19
#define DW1000_SCK_PIN		16
#define DW1000_MISO_PIN		18
#define DW1000_MOSI_PIN		20
#define DW1000_SS_PIN		17

#define UUS_TO_DWT_TIME				63897

typedef union {
    uint64_t    ts;
    uint8_t     ts_bytes[5];

    uint32_t    ts_low_32;
} dwm1000_ts_t;

dwm1000_ts_t dwm1000_get_system_time_u64(void);
dwm1000_ts_t dwm1000_get_rx_timestamp_u64(void);
static inline void dwm1000_ts_to_pu8(dwm1000_ts_t ts, uint8_t* p8)
{
    for(int i = 0; i < 5; i++)
        p8[i] = ts.ts_bytes[i];
}

static inline dwm1000_ts_t dwm1000_pu8_to_ts(const uint8_t* p8)
{
    dwm1000_ts_t _ts = { 0 };
    for(int i = 0; i < 5; i++)
        _ts.ts_bytes[i] = p8[i];
    return _ts;
}

static inline void dwm1000_ts_u64_to_pu8(uint64_t ts, uint8_t* p8)
{
    dwm1000_ts_t _ts = { 0 };
    _ts.ts = ts;

    dwm1000_ts_to_pu8(_ts,p8);
}

int dwm1000_phy_init();
void dwm1000_phy_release();

void dwm1000_irq_enable();
void dwm1000_irq_disable();

#endif /* DW1000_H_ */


