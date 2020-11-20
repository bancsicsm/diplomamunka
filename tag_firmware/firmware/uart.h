#ifndef UART_H
#define UART_H

#include <stdint.h>
#include <stdbool.h>

void uart_init();
void uart_put_as_hex(uint8_t* x, int length);
void uart_put(uint8_t* x, int length);
void uart_puts(char* s);
void uart_test(bool block);

#endif // UART_H
