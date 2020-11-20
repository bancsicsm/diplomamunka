#include "address_handler.h"
#include "log.h"
#include <nrf.h>
#include <stdio.h>
#include <inttypes.h>

#define TAG "addr"

typedef struct
{
    uint16_t    group_id;
    uint16_t    device_id:15;
    uint8_t     is_anchor:1;
} __attribute__((packed)) uicr_data_t;
volatile uicr_data_t m_uicr_data  __attribute__ ((section(".uicr_customer")));

static uint64_t m_mac_address;
static uint16_t m_virtual_address;
static uint16_t m_group_id;
static bool     m_is_anchor;
static char     m_device_name[26];


void addr_handler_init() {
    m_mac_address = *((uint64_t*)&NRF_FICR->DEVICEADDR[0]);
    m_mac_address &= 0x0000ffffffffffff;
    m_mac_address |= 0x0000c00000000000;

	LOGI(TAG, "MAC addr: 0x%" PRIX64 "\n", m_mac_address);
    LOGT(TAG, "UICR: 0x%08X\n", *((uint32_t*)&m_uicr_data));

	if(*(uint32_t*)&m_uicr_data == 0xFFFFFFFF)
	{
		ERROR(TAG,"No address specified (uicr is uninitialized).\n");
	}

    m_virtual_address = m_uicr_data.device_id;
    m_is_anchor = m_uicr_data.is_anchor;
    m_group_id = m_uicr_data.group_id;

    if(m_is_anchor)
    {
        sprintf(m_device_name, "Anchor 0x%04X", addr_handler_get_virtual_addr());
    }
    else
    {
        sprintf(m_device_name, "Tag 0x%04X", addr_handler_get_virtual_addr());
    }

    LOGI(TAG, "device: %s\n", m_device_name);
    LOGI(TAG, "group: 0x%04X\n", addr_handler_get_group_id());
}

uint64_t addr_handler_get_mac_addr() {
    return m_mac_address;
}

uint16_t addr_handler_get_virtual_addr() {
    return m_virtual_address;
}

bool addr_handler_is_anchor()
{
    return m_is_anchor;
}

char *addr_handler_get_device_name()
{
    return m_device_name;
}


uint16_t addr_handler_get_group_id()
{
    return m_group_id;
}


