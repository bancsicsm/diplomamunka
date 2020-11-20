#include "accel_service.h"
#include "ble_srv_common.h"
#include "nrf_log.h"
#include "log.h"
#include "app_scheduler.h"
#include <sdk_macros.h>

#define TAG "accs"

static ble_accs_t m_accs;
static uint16_t m_conn_handle = BLE_CONN_HANDLE_INVALID;
static ble_accs_status_callback_t   m_status_callback = NULL;

uint32_t ble_accs_init(ble_accs_status_callback_t cb)
{
	LOGI(TAG,"Data format: %d\n", sizeof(df_accel_mode_t));

    ble_accs_t* p_accs = &m_accs;
    m_status_callback = cb;

    uint32_t              err_code;
    ble_uuid_t            ble_uuid;
    ble_add_char_params_t add_char_params;

    ble_uuid128_t base_uuid = {ACCS_UUID_BASE};
    err_code = sd_ble_uuid_vs_add(&base_uuid, &p_accs->uuid_type);
    VERIFY_SUCCESS(err_code);

    ble_uuid.type = p_accs->uuid_type;
    ble_uuid.uuid = ACCS_UUID_SERVICE;

    err_code = sd_ble_gatts_service_add(BLE_GATTS_SRVC_TYPE_PRIMARY, &ble_uuid, &p_accs->service_handle);
    VERIFY_SUCCESS(err_code);

    memset(&add_char_params, 0, sizeof(add_char_params));
    add_char_params.uuid              = ACCS_UUID_SENSOR_CHAR;
    add_char_params.uuid_type         = p_accs->uuid_type;
    add_char_params.init_len          = sizeof(df_accel_info_t);
    add_char_params.max_len           = sizeof(df_accel_info_t);
    add_char_params.char_props.read   = 1;
    add_char_params.char_props.notify = 1;

    add_char_params.read_access       = SEC_OPEN;
    add_char_params.cccd_write_access = SEC_OPEN;

    err_code = characteristic_add(p_accs->service_handle,
                                  &add_char_params,
                                  &p_accs->sensor_char_handles);

    uint8_t status_value = 0;
    memset(&add_char_params, 0, sizeof(add_char_params));
    add_char_params.uuid              = ACCS_UUID_CONTROL_CHAR;
    add_char_params.uuid_type         = p_accs->uuid_type;
	add_char_params.init_len          = sizeof(df_accel_mode_t);
	add_char_params.max_len           = sizeof(df_accel_mode_t);
    add_char_params.p_init_value      = &status_value;
    add_char_params.char_props.read   = 1;
    add_char_params.char_props.write  = 1;

    add_char_params.read_access       = SEC_OPEN;
    add_char_params.write_access      = SEC_OPEN;

    err_code = characteristic_add(p_accs->service_handle,
                                  &add_char_params,
                                  &p_accs->control_char_handles);

    return err_code;
}

static void on_status_changed_sched_handler(void *p_event_data, uint16_t event_size)
{
	df_accel_mode_t* status = (df_accel_mode_t*)p_event_data;

    if(m_status_callback != NULL &&
			event_size == sizeof(df_accel_mode_t))
        m_status_callback(*status);
}

void ble_accs_on_ble_evt(ble_evt_t const * p_ble_evt, void * p_context)
{
    switch (p_ble_evt->header.evt_id)
    {
    case BLE_GAP_EVT_CONNECTED:
        LOGI(TAG,"Connected, ACCS\n");
        m_conn_handle = p_ble_evt->evt.gap_evt.conn_handle;
        break;
    case BLE_GAP_EVT_DISCONNECTED:
        LOGI(TAG,"Disconnected, ACCS\n");
        m_conn_handle = BLE_CONN_HANDLE_INVALID;

        {
			df_accel_mode_t status = {
				.mode = ACCS_MODE_POWERDOWN,
				.hpf_enabled = false
			};
			app_sched_event_put((const void*)&status, sizeof(df_accel_mode_t), on_status_changed_sched_handler);
        }
        break;
    case BLE_GATTS_EVT_WRITE:
        {
            ble_gatts_evt_write_t const * p_evt_write = &p_ble_evt->evt.gatts_evt.params.write;
            if(p_evt_write->handle == m_accs.control_char_handles.value_handle &&
					p_evt_write->len == sizeof(df_accel_mode_t))
            {
				app_sched_event_put((const void*)&p_evt_write->data[0], sizeof(df_accel_mode_t), on_status_changed_sched_handler);
            }
        }
        break;
    }
}

uint32_t ble_accs_send_values(df_accel_info_t* acc_data)
{
    if(m_conn_handle != BLE_CONN_HANDLE_INVALID)
    {
        ble_gatts_hvx_params_t params;
        uint16_t len = sizeof(df_accel_info_t);

        memset(&params, 0, sizeof(params));
        params.type   = BLE_GATT_HVX_NOTIFICATION;
        params.handle = m_accs.sensor_char_handles.value_handle;
        params.p_data = (uint8_t*)acc_data;
        params.p_len  = &len;

        return sd_ble_gatts_hvx(m_conn_handle, &params);
    }
    else
    {
        return NRF_SUCCESS;
    }
}


uint8_t ble_accs_get_uuid_type()
{
    return m_accs.uuid_type;
}

void ble_accs_set_status_callback(ble_accs_status_callback_t cb)
{
    m_status_callback = cb;
}

NRF_SDH_BLE_OBSERVER(m_accs_obs,BLE_LBS_BLE_OBSERVER_PRIO,ble_accs_on_ble_evt, &m_accs);


