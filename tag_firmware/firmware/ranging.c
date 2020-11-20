#include "ranging.h"
#include <string.h>
#include <tdoa_as_func_test.h>
#include "log.h"
#include "utils.h"

#define TAG "ranging"

#define SPEED_OF_LIGHT					299702547

#define ANT_DLY_CH1                                             16414
#define TX_ANT_DLY                                              ANT_DLY_CH1             //16620 //16436 /* Default antenna delay values for 64 MHz PRF */
#define RX_ANT_DLY                                              ANT_DLY_CH1

#define CORRECT_CLOCK_DIFF(x)   x = ((x) < 0)?((x) + (1ll << 40)):(x)

static uint64_t tss_0[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1];
static uint64_t tss_1[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1];
static uint64_t tss_2[TIMING_ANCHOR_COUNT+1][TIMING_ANCHOR_COUNT+1];
static uint8_t trid;
static  uint16_t src_max=TIMING_ANCHOR_COUNT;
static  uint16_t src_max_accu=0;

static dist_diff outpos;



typedef struct {
    dwm1000_ts_t        anchor_msg_tx_ts;
    dwm1000_ts_t        anchor_msg_rx_ts;
    dwm1000_ts_t        tag_msg_rx_ts;
} anchor_ranging_info_t;

static dwm1000_ts_t                 m_last_tag_tx;
static anchor_ranging_info_t        m_anchor_infos_1[TIMING_ANCHOR_COUNT];
static anchor_ranging_info_t        m_anchor_infos_2[TIMING_ANCHOR_COUNT];
static anchor_ranging_info_t*       m_anchor_infos_new;
static anchor_ranging_info_t*       m_anchor_infos_old;
static int16_t                      m_anchor_distances[TIMING_ANCHOR_NCOMB2+1];
static uint16_t                     m_tag_virtual_addr;

//static uint16_t do_ranging(dwm1000_ts_t ts1, dwm1000_ts_t ts2, dwm1000_ts_t ts3, dwm1000_ts_t ts4, dwm1000_ts_t ts5, dwm1000_ts_t ts6)
//{
//    LOGI(TAG,"ts1 = %" PRIx64 "\n", ts1);
//    LOGI(TAG,"ts2 = %" PRIx64 "\n", ts2);
//    LOGI(TAG,"ts3 = %" PRIx64 "\n", ts3);
//    LOGI(TAG,"ts4 = %" PRIx64 "\n", ts4);
//    LOGI(TAG,"ts5 = %" PRIx64 "\n", ts5);
//    LOGI(TAG,"ts6 = %" PRIx64 "\n", ts6);
//
//#if TIMING_SUPERFRAME_LENGTH_MS < 60
//    uint32_t Tround1 = ts4.ts_low_32 - ts1.ts_low_32;
//    uint32_t Tround2 = ts6.ts_low_32 - ts3.ts_low_32;
//    uint32_t Treply2 = ts5.ts_low_32 - ts4.ts_low_32;
//    uint32_t Treply1 = ts3.ts_low_32 - ts2.ts_low_32;
//#else
//    int64_t Tround1 = ts4.ts - ts1.ts;
//    int64_t Tround2 = ts6.ts - ts3.ts;
//    int64_t Treply2 = ts5.ts - ts4.ts;
//    int64_t Treply1 = ts3.ts - ts2.ts;
//
//    CORRECT_CLOCK_DIFF(Tround1);
//    CORRECT_CLOCK_DIFF(Tround2);
//    CORRECT_CLOCK_DIFF(Treply1);
//    CORRECT_CLOCK_DIFF(Treply2);
//#endif
//
//    int64_t Ra = Tround1 - RX_ANT_DLY - TX_ANT_DLY;
//    int64_t Rb = Tround2 - RX_ANT_DLY - TX_ANT_DLY;
//    int64_t Da = Treply2 + TX_ANT_DLY + RX_ANT_DLY;
//    int64_t Db = Treply1 + TX_ANT_DLY + RX_ANT_DLY;
//
//    float tof_dtu = ((Ra * Rb - Da * Db) / (float)(Ra + Rb + Da + Db));
//    float tof = tof_dtu * (float)DWT_TIME_UNITS;
//    float distance = tof * SPEED_OF_LIGHT;
//
//    return distance * 1000;
//}

void ranging_init(uint16_t tag_virtual_addr)
{
    m_tag_virtual_addr = tag_virtual_addr;
    ERROR_CHECK(tag_virtual_addr <= TIMING_TAG_COUNT, true);

    m_last_tag_tx.ts = 0;
    memset(m_anchor_infos_1, 0, TIMING_ANCHOR_COUNT * sizeof(anchor_ranging_info_t));
    memset(m_anchor_infos_2, 0, TIMING_ANCHOR_COUNT * sizeof(anchor_ranging_info_t));
    m_anchor_infos_new = m_anchor_infos_1;
    m_anchor_infos_old = m_anchor_infos_2;
}

void ranging_on_new_superframe()
{
    m_anchor_infos_new = (m_anchor_infos_new == m_anchor_infos_1)?(m_anchor_infos_2):(m_anchor_infos_1);
    m_anchor_infos_old = (m_anchor_infos_old == m_anchor_infos_1)?(m_anchor_infos_2):(m_anchor_infos_1);
    memset(m_anchor_infos_new, 0, TIMING_ANCHOR_COUNT * sizeof(anchor_ranging_info_t));
    memset(m_anchor_distances, 0, (TIMING_ANCHOR_NCOMB2+1) * sizeof(int16_t));
}

void ranging_on_tag_tx(dwm1000_ts_t tx_ts)
{
    m_last_tag_tx = tx_ts;
}

void ranging_on_anchor_rx(dwm1000_ts_t rx_ts, sf_anchor_msg_t * msg)
{
    if(msg->hdr.src_id >= TIMING_ANCHOR_COUNT)
    {
        LOGE(TAG,"anchor src id is wrong (%d)\n", msg->hdr.src_id);
        return;
    }
    trid=msg->tr_id;

   // anchor_ranging_info_t old_ri = m_anchor_infos_old[msg->hdr.src_id];
    anchor_ranging_info_t* new_ri = &m_anchor_infos_new[msg->hdr.src_id];
    new_ri->anchor_msg_rx_ts = rx_ts;
    new_ri->anchor_msg_tx_ts = dwm1000_pu8_to_ts(msg->tx_ts);
    new_ri->tag_msg_rx_ts = dwm1000_pu8_to_ts(msg->tags[m_tag_virtual_addr].rx_ts);

    LOGT(TAG, "trid:%d, msg source:%d\n",trid,msg->hdr.src_id );

   if(new_ri->anchor_msg_rx_ts.ts != 0)
    {
            if (trid%2==0)
            {
               src_max_accu = msg->hdr.src_id;
            }
            if (trid%2==1)
            {
                src_max = src_max_accu;
            }

          tss_1[TIMING_ANCHOR_COUNT][msg->hdr.src_id] = dwm1000_pu8_to_ts(msg->tags[m_tag_virtual_addr].rx_ts).ts;

           for (int cnt = 0; cnt < TIMING_ANCHOR_COUNT; cnt++)
           {
               if (cnt > msg->hdr.src_id) {
                   tss_1[cnt][msg->hdr.src_id] = dwm1000_pu8_to_ts(msg->anchors[cnt].rx_ts).ts;
               }
               if (cnt < msg->hdr.src_id) {
                   tss_2[cnt][msg->hdr.src_id] = dwm1000_pu8_to_ts(msg->anchors[cnt].rx_ts).ts;
               }

           }

           if (msg->hdr.src_id == src_max)
           {
              // if (trid%16!=0) {
                   outpos = tdoa_as_func_test(tss_0, tss_1, trid, src_max + 1);

               for (short i = 0; i <= TIMING_ANCHOR_COUNT; i++)
               {
                   for (short j = 0; j <= TIMING_ANCHOR_COUNT; j++)
                   {
                       tss_0[i][j] = tss_1[i][j];
                       tss_1[i][j] = tss_2[i][j];
                       tss_2[i][j] = 0;
                   }
               }
            //   }
               //write output to ble
               m_anchor_distances[0] = trid;
               for (short i = 0; i < TIMING_ANCHOR_NCOMB2; i++)
               {
                   m_anchor_distances[i + 1] = outpos.dd[i];
                   LOGT(TAG, "%d\n", m_anchor_distances[i + 1]);
               }
           }

    }

   else
    {
        LOGW(TAG, "ranging failed: %d\n", msg->hdr.src_id);
    }

}


int16_t *ranging_get_distances()
{
    return m_anchor_distances;
}
