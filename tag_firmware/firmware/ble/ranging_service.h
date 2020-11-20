#ifndef RANGING_SERVICE_H
#define RANGING_SERVICE_H

#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include "ble.h"
#include "ble_srv_common.h"
#include "nrf_sdh_ble.h"
#include "timing.h"
#include "data_format.h"
#include "impl_tag.h"


// f45aacdd-00a6-413e-87db-580f0cab9adc
#define RS_UUID_BASE        {0xdc, 0x9a, 0xab, 0x0c, 0x0f, 0x58, 0xdb, 0x87, \
                              0x3E, 0x41, 0xa6, 0x00, 0x00, 0x00, 0x5a, 0xf4}
#define RS_UUID_SERVICE      0x1000
#define RS_UUID_INFO_CHAR     0x1001
#define RS_UUID_RANGING_CHAR  0x1002
#define RS_UUID_MODE_CHAR     0x1003

typedef struct ble_rs_s ble_rs_t;
typedef void (*ble_rs_tag_mode_callback_t)(tag_mode_t mode);

struct ble_rs_s
{
    uint16_t                    service_handle;      /**< Handle of Ranging Service (as provided by the BLE stack). */
    ble_gatts_char_handles_t    info_char_handles;    /**< Handles related to the Example Characteristic. */
    ble_gatts_char_handles_t    mode_char_handles;
    ble_gatts_char_handles_t    ranging_char_handles;
    uint8_t                     uuid_type;           /**< UUID type for the Ranging Service. */

    ble_rs_tag_mode_callback_t  mode_callback;
};

uint32_t ble_rs_init();
uint32_t ble_rs_send_ranging(df_ranging_info_t* ranging_data);
uint32_t ble_rs_send_anchor_ranging_info(df_anchor_ranging_info_t *ranging_data);
uint8_t  ble_rs_get_uuid_type();
void     ble_rs_set_tag_mode_callback(ble_rs_tag_mode_callback_t cb);
df_device_info_t* ble_rs_get_device_info();

#endif // RANGING_SERVICE_H
