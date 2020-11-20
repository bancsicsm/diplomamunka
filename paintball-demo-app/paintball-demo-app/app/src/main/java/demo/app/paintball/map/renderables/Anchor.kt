package demo.app.paintball.map.renderables

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R

class Anchor(posX: Int, posY: Int) : Renderable() {

    companion object {
        const val size = 2
    }

    var posX: Int = posX
        private set
    var posY: Int = posY
        private set

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_anchor)

    override fun render(canvas: Canvas) {
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