package demo.app.paintball.config.map

import demo.app.paintball.R

class GyenesMapConfig : MapConfig() {

    override val imageDrawableId = R.drawable.map_gyenes

    override val imageWidthPx = 2_986

    override val mapOrientation = 270

    override val minZoom = 4.8
    override val maxZoom = 1.5

    override val anchorOriginPosX = 5_000
    override val anchorOriginPosY = 10_000

    override val anchorOriginPxX = 800

    override val anchors = listOf(
        intArrayOf(0, 0, 1_100),
        intArrayOf(3_800, 0, 1_100),
        intArrayOf(0, 4_100, 1_100),
        intArrayOf(3_800, 4_100, 1_100),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )
}