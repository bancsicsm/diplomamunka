#ifndef TIMING_H
#define TIMING_H

#include <stdint.h>

#define TIMING_DISCOVERY_SUPERFRAME_COUNT   5

#define TIMING_ANCHOR_COUNT             8
#define TIMING_TAG_COUNT                12
#define TIMING_ANCHOR_NCOMB2            28

#define TIMING_ANCHOR_MESSAGE_LENGTH_US                       5000
#define TIMING_MESSAGE_TX_PREFIX_TIME_US                      (1051 + 400)          // RMARKER delay + DW1000 comm delay

#define TIMING_TAG_MESSAGE_LENGTH_US                          5000

#define TIMING_SUPERFRAME_LENGTH_US     (TIMING_ANCHOR_COUNT * TIMING_ANCHOR_MESSAGE_LENGTH_US + TIMING_TAG_COUNT * TIMING_TAG_MESSAGE_LENGTH_US)
#define TIMING_SUPERFRAME_LENGTH_MS     (TIMING_SUPERFRAME_LENGTH_US/1000)


#define SF_HEADER_FCTRL_MSG_TYPE_ANCHOR_MESSAGE    0x01
#define SF_HEADER_FCTRL_MSG_TYPE_TAG_MESSAGE       0x02

typedef struct
{
    uint8_t          fctrl;
    uint8_t          padding;
    uint16_t         group_id;
    uint16_t         src_id;
} __attribute__((packed)) sf_header_t;

typedef struct {
    uint8_t          rx_ts[5];
} __attribute__((packed)) rx_info_t;

typedef struct {
    sf_header_t     hdr;
    uint8_t         tr_id;
    uint8_t         tx_ts[5];

    rx_info_t       anchors[TIMING_ANCHOR_COUNT];
    rx_info_t       tags[TIMING_TAG_COUNT];
} __attribute__((packed)) sf_anchor_msg_t;

typedef struct {
    sf_header_t     hdr;

	uint8_t         tr_id;
	uint8_t         tx_ts[5];

	rx_info_t       anchors[TIMING_ANCHOR_COUNT];
} __attribute__((packed)) sf_tag_msg_t;

#define SF_MAX_MESSAGE_SIZE    (sizeof(sf_anchor_msg_t))

#endif // TIMING_H
