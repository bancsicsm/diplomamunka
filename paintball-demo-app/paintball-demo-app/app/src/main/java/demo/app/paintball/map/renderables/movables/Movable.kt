package demo.app.paintball.map.renderables.movables

import android.graphics.Canvas
import android.graphics.Rect
import android.os.SystemClock
import demo.app.paintball.map.renderables.Map
import demo.app.paintball.map.renderables.Player
import demo.app.paintball.map.renderables.Renderable

abstract class Movable(val name: String) : Renderable() {

    companion object {
        const val size = 3
        const val MAX_TIME_BETWEEN_POSITION_UPDATES = 1_500
    }

    var posX: Int = 0
    var posY: Int = 0
        set(value) {
            field = value
            lastUpdate = SystemClock.uptimeMillis()
        }

    private var lastUpdate: Long = SystemClock.uptimeMillis()

    override fun render(canvas: Canvas) {
        if (isVisible()) {
            val distanceFromPlayerX = (Player.posX - posX) / Map.zoom
            val distanceFromPlayerY = (Player.posY - posY) / Map.zoom
            val translateX = (screenWidth / 2 - distanceFromPlayerX).toInt() - (image.width / size / 2)
            val translateY = (screenHeight / 2 - distanceFromPlayerY).toInt() - (image.height / size / 2)

            val src = Rect(0, 0, image.width, image.height)
            val dst = Rect(
                translateX,
                translateY,
                translateX + image.width / size,
                translateY + image.height / size
            )
            canvas.drawBitmap(image, src, dst, null)
        }
    }

    private fun isVisible() =
        (SystemClock.uptimeMillis() - lastUpdate) < MAX_TIME_BETWEEN_POSITION_UPDATES
}