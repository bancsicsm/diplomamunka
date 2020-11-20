#include "ranging_anchor.h"
#include <string.h>
#include "log.h"
#include "utils.h"

#define TAG "ranging"

#define SPEED_OF_LIGHT					299702547

#define ANT_DLY_CH1                                             16414
#define TX_ANT_DLY                                              ANT_DLY_CH1             //16620 //16436 /* Default antenna delay values for 64 MHz PRF */
#define RX_ANT_DLY                                              ANT_DLY_CH1

#define CORRECT_CLOCK_DIFF(x)   x = ((x) < 0)?((x) + (1ll << 40)):(x)

typedef struct {
    dwm1000_ts_t        tx_ts;
    dwm1000_ts_t        rx_ts[TIMING_ANCHOR_COUNT];
} anchor_ranging_info_t;


typedef struct {
    anchor_ranging_info_t   anchor_info[TIMING_ANCHOR_COUNT];
} anchor_ranging_db_t;

static anchor_ranging_db_t          m_ranging_db_1;
static anchor_ranging_db_t          m_ranging_db_2;
static anchor_ranging_db_t*         m_ranging_db_new;
static anchor_ranging_db_t*         m_ranging_db_old;

static uint16_t                     m_anchor_distances[TIMING_ANCHOR_NCOMB2];


static uint16_t do_ranging(dwm1000_ts_t ts1, dwm1000_ts_t ts2, dwm1000_ts_t ts3, dwm1000_ts_t ts4, dwm1000_ts_t ts5, dwm1000_ts_t ts6)
{
#if TIMING_SUPERFRAME_LENGTH_MS < 60
    uint32_t Tround1 = ts4.ts_low_32 - ts1.ts_low_32;
    uint32_t Tround2 = ts6.ts_low_32 - ts3.ts_low_32;
    uint32_t Treply2 = ts5.ts_low_32 - ts4.ts_low_32;
    uint32_t Treply1 = ts3.ts_low_32 - ts2.ts_low_32;
#else
    int64_t Tround1 = ts4.ts - ts1.ts;
    int64_t Tround2 = ts6.ts - ts3.ts;
    int64_t Treply2 = ts5.ts - ts4.ts;
    int64_t Treply1 = ts3.ts - ts2.ts;

    CORRECT_CLOCK_DIFF(Tround1);
    CORRECT_CLOCK_DIFF(Tround2);
    CORRECT_CLOCK_DIFF(Treply1);
    CORRECT_CLOCK_DIFF(Treply2);
#endif

    int64_t Ra = Tround1 - RX_ANT_DLY - TX_ANT_DLY;
    int64_t Rb = Tround2 - RX_ANT_DLY - TX_ANT_DLY;
    int64_t Da = Treply2 + TX_ANT_DLY + RX_ANT_DLY;
    int64_t Db = Treply1 + TX_ANT_DLY + RX_ANT_DLY;

    float tof_dtu = ((Ra * Rb - Da * Db) / (float)(Ra + Rb + Da + Db));
    float tof = tof_dtu * (float)DWT_TIME_UNITS;
    float distance = tof * SPEED_OF_LIGHT;

    return distance * 1000;
}


void ranging_anchor_init()
{
    memset(&m_ranging_db_1, 0, sizeof(anchor_ranging_db_t));
    memset(&m_ranging_db_2, 0, sizeof(anchor_ranging_db_t));
    m_ranging_db_new = &m_ranging_db_1;
    m_ranging_db_old = &m_ranging_db_2;

    memset(m_anchor_distances, 0, TIMING_ANCHOR_NCOMB2 * sizeof(uint16_t));
}

void ranging_anchor_on_new_superframe()
{
    memset(m_anchor_distances, 0, TIMING_ANCHOR_NCOMB2 * sizeof(uint16_t));

    int dist_idx = 0;
    for(int ai1 = 0; ai1 < TIMING_ANCHOR_COUNT; ai1++)
    {
        for(int ai2 = ai1 + 1; ai2 < TIMING_ANCHOR_COUNT; ai2++)
        {
            dwm1000_ts_t poll_tx_ts = m_ranging_db_old->anchor_info[ai1].tx_ts;
            dwm1000_ts_t poll_rx_ts = m_ranging_db_old->anchor_info[ai2].rx_ts[ai1];
            dwm1000_ts_t resp_tx_ts = m_ranging_db_old->anchor_info[ai2].tx_ts;
            dwm1000_ts_t resp_rx_ts = m_ranging_db_old->anchor_info[ai1].rx_ts[ai2];
            dwm1000_ts_t fina_tx_ts = m_ranging_db_new->anchor_info[ai1].tx_ts;
            dwm1000_ts_t fina_rx_ts = m_ranging_db_new->anchor_info[ai2].rx_ts[ai1];

            if(poll_tx_ts.ts != 0 && poll_rx_ts.ts != 0 &&
                    resp_tx_ts.ts != 0 && resp_rx_ts.ts != 0 &&
                    fina_tx_ts.ts != 0 && fina_rx_ts.ts != 0)
            {
                m_anchor_distances[dist_idx] = do_ranging(poll_tx_ts, poll_rx_ts, resp_tx_ts, resp_rx_ts, fina_tx_ts, fina_rx_ts);
                LOGI(TAG, "dist (%d->%d): %" PRIu16 "\n", ai1, ai2, m_anchor_distances[dist_idx]);
            }

            dist_idx++;
        }
    }

    m_ranging_db_new = (m_ranging_db_new == &m_ranging_db_1)?(&m_ranging_db_2):(&m_ranging_db_1);
    m_ranging_db_old = (m_ranging_db_old == &m_ranging_db_1)?(&m_ranging_db_2):(&m_ranging_db_1);
    memset(m_ranging_db_new, 0, sizeof(anchor_ranging_db_t));

}


void ranging_anchor_on_anchor_rx(dwm1000_ts_t rx_ts, sf_anchor_msg_t *msg)
{
    if(msg->hdr.src_id >= TIMING_ANCHOR_COUNT)
    {
        LOGE(TAG,"anchor src id is wrong (%d)\n", msg->hdr.src_id);
        return;
    }

    m_ranging_db_new->anchor_info[msg->hdr.src_id].tx_ts = dwm1000_pu8_to_ts(msg->tx_ts);
    for(int i = 0; i < msg->hdr.src_id; i++)
    {
        m_ranging_db_new->anchor_info[msg->hdr.src_id].rx_ts[i] = dwm1000_pu8_to_ts(msg->anchors[i].rx_ts);
    }

    for(int i = msg->hdr.src_id; i < TIMING_ANCHOR_COUNT; i++)
    {
        m_ranging_db_old->anchor_info[msg->hdr.src_id].rx_ts[i] = dwm1000_pu8_to_ts(msg->anchors[i].rx_ts);
    }
}


uint16_t *ranging_anchor_get_distances()
{
    return m_anchor_distances;
}
