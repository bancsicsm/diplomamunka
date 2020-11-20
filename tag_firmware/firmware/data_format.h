#ifndef DATA_FORMAT_H
#define DATA_FORMAT_H

#include <stdint.h>
#include "timing.h"
#include "lis2dh12.h"

typedef struct
{
    uint16_t        ts;
    uint16_t        values[TIMING_ANCHOR_NCOMB2+1];
} __attribute__((packed)) df_ranging_info_t;

typedef struct
{
    uint16_t        ts;
    uint16_t        values[TIMING_ANCHOR_NCOMB2];
} __attribute__((packed)) df_anchor_ranging_info_t;

#define ACCEL_FIFO_BURST_SIZE             10

typedef struct {
    uint16_t                ts;
    lis2dh12_data_t         values[ACCEL_FIFO_BURST_SIZE];
} __attribute__((packed)) df_accel_info_t;


typedef struct {
    uint16_t                group_id;
    uint16_t                dev_id;

    uint16_t                 anchor_count;
    uint16_t                 tag_count;

    uint32_t                failed_notification_count;
    uint32_t                notification_count;
} __attribute__((packed)) df_device_info_t;
#endif // DATA_FORMAT_H
