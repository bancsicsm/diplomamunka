#ifndef LOG_H
#define LOG_H

#include "SEGGER_RTT.h"
#include "nrf_assert.h"

#define assert ASSERT

#define LOGTS(anchor,trid,tsid,ts) { \
	  char _out[256]; \
	  sprintf(_out,"%04X %d %d %" PRIu64 "\n", anchor, trid, tsid, ts); \
	  SEGGER_RTT_WriteString(0, _out); \
	}

#define ENABLE_LOG
#ifdef ENABLE_LOG
#define LOGE(tag,msg,...) { \
      char _out[256]; \
      sprintf(_out,"[E," tag "] " msg, ##__VA_ARGS__); \
      SEGGER_RTT_WriteString(0, _out); \
    }

#define LOGW(tag,msg,...) { \
      char _out[256]; \
      sprintf(_out,"[W," tag "] " msg, ##__VA_ARGS__); \
      SEGGER_RTT_WriteString(0, _out); \
    }

#define LOGI(tag,msg,...) { \
    char _out[256]; \
    sprintf(_out,"[I," tag "] " msg, ##__VA_ARGS__); \
    SEGGER_RTT_WriteString(0, _out); \
  }
#else
#define LOGE(tag,msg,...)
#define LOGW(tag,msg,...)
#define LOGI(tag,msg,...)
#endif

#define TRACE_LOG
#ifdef TRACE_LOG
#define LOGT(tag,msg,...) { \
    char _out[256]; \
    sprintf(_out,"[T] " msg, ##__VA_ARGS__); \
    SEGGER_RTT_WriteString(0, _out); \
  }
#else
#define LOGT(tag,msg,...)
#endif

#define ERROR(tag,msg) { \
    LOGE(tag,msg)    \
    while(1) {} \
}

#endif // LOG_H
