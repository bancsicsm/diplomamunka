package demo.app.paintball.config.map

import demo.app.paintball.R

class GardenMapConfig : MapConfig() {

    override val imageDrawableId = R.drawable.map_garden

    override val imageWidthPx = 2_049

    override val mapOrientation = 46

    override val minZoom = 3.3
    override val maxZoom = 1.3

    override val anchorOriginPosX = 2_470
    override val anchorOriginPosY = 14_600

    override val anchorOriginPxX = 175

    override val anchors = listOf(
        intArrayOf(0, 0, 800),
        intArrayOf(15_000, 0, 800),
        intArrayOf(0, 10000, 800),
        intArrayOf(15_000, 8_700, 800),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0),
        intArrayOf(0, 0, 0)
    )
}