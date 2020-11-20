package demo.app.paintball.config

import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.config.map.GardenMapConfig
import demo.app.paintball.config.map.GyenesMapConfig
import demo.app.paintball.config.map.MapConfig

object Config {

    val mapConfig: MapConfig = if (context.getString(R.string.map) == "garden") {
        GardenMapConfig()
    } else {
        GyenesMapConfig()
    }

}