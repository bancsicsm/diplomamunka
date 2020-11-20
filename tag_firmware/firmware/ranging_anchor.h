#ifndef RANGING_ANCHOR_H
#define RANGING_ANCHOR_H

#include <stdint.h>
#include "timing.h"
#include "dwm_utils.h"

void ranging_anchor_init();
void ranging_anchor_on_new_superframe();
void ranging_anchor_on_anchor_rx(dwm1000_ts_t rx_ts, sf_anchor_msg_t* msg);

uint16_t* ranging_anchor_get_distances();
static inline uint16_t  ranging_anchor_distances_length() { return TIMING_ANCHOR_NCOMB2; }

#endif // RANGING_H
