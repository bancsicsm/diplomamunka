#ifndef ADDRESS_HANDLER_H
#define ADDRESS_HANDLER_H

#include <stdbool.h>
#include <stdint.h>

void addr_handler_init();
uint64_t addr_handler_get_mac_addr();
uint16_t addr_handler_get_virtual_addr();
bool addr_handler_is_anchor();
char* addr_handler_get_device_name();

uint16_t addr_handler_get_group_id();

#endif // ADDRESS_HANDLER_H
