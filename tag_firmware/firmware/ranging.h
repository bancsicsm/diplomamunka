#ifndef RANGING_H
#define RANGING_H

#include <stdint.h>
#include "timing.h"
#include "dwm_utils.h"

void ranging_init(uint16_t tag_virtual_addr);
void ranging_on_new_superframe();
void ranging_on_tag_tx(dwm1000_ts_t tx_ts);
void ranging_on_anchor_rx(dwm1000_ts_t rx_ts, sf_anchor_msg_t* msg);

int16_t* ranging_get_distances();

#endif // RANGING_H
