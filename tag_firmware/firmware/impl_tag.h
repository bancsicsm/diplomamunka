#ifndef IMPL_TAG_H
#define IMPL_TAG_H

typedef enum {
    TAG_MODE_POWERDOWN = 0x00,
    TAG_MODE_TAG_RANGING = 0x01,
    TAG_MODE_ANCHOR_RANGING = 0x02
} tag_mode_t;

int impl_tag_init();

#endif // IMPL_ANCHOR_H
