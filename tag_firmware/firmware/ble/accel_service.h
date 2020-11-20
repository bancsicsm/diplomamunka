#ifndef ACCEL_SERVICE_H
#define ACCEL_SERVICE_H

#include <stdint.h>
#include <stdbool.h>
#include <string.h>
#include "ble.h"
#include "ble_srv_common.h"
#include "nrf_sdh_ble.h"
#include "timing.h"
#include "data_format.h"

// f45aacdd-00a6-413e-87db-580f0cab9adc
#define ACCS_UUID_BASE        {0xdc, 0x9a, 0xab, 0x0c, 0x0f, 0x58, 0xdb, 0x87, \
                              0x3E, 0x41, 0xa6, 0x00, 0x00, 0x00, 0x5a, 0xf4}
#define ACCS_UUID_SERVICE      0x2000
#define ACCS_UUID_CONTROL_CHAR  0x2001
#define ACCS_UUID_SENSOR_CHAR  0x2002

typedef enum  {
	ACCS_MODE_POWERDOWN = 0x00,
	ACCS_MODE_1HZ = 0x01,
	ACCS_MODE_10HZ = 0x02,
	ACCS_MODE_25HZ = 0x03,
	ACCS_MODE_50HZ = 0x04,
} accs_mode_t;

typedef struct {
	accs_mode_t		mode:3;
	bool			hpf_enabled:1;
} __attribute__((packed)) df_accel_mode_t;

typedef struct ble_accs_s ble_accs_t;
typedef void (*ble_accs_status_callback_t)(df_accel_mode_t mode);

struct ble_accs_s
{
    uint16_t                    service_handle;
    ble_gatts_char_handles_t    control_char_handles;
    ble_gatts_char_handles_t    sensor_char_handles;
    uint8_t                     uuid_type;
};

uint32_t ble_accs_init();
uint32_t ble_accs_send_values(df_accel_info_t* acc_data);
void ble_accs_set_status_callback(ble_accs_status_callback_t cb);
uint8_t ble_accs_get_uuid_type();

#endif // RANGING_SERVICE_H
