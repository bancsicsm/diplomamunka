#ifndef UTILS_H
#define UTILS_H

#include <stdint.h>

void my_error_function(int code, const char* filename, const int line);

#define ERROR_CHECK(x,y) \
    do { \
        if((x) != (y)) \
        { \
            my_error_function(x,__FILE__,__LINE__); \
        } \
    } while(0);

#define ENSURE_ALIGN(A,B) (((A) + (B) - 1) / (B) * (B))

void utils_init();

void utils_start_execution_timer();
uint32_t utils_stop_execution_timer();

void utils_use_tick_timer();
void utils_release_tick_timer();
uint32_t utils_get_tick_time();


#endif // UTILS_H
