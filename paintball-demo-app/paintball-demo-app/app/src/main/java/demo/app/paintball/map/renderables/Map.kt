package demo.app.paintball.map.renderables

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.config.Config

class Map : Renderable() {

    companion object {
        val MIN_ZOOM = Config.mapConfig.minZoom
        val MAX_ZOOM = Config.mapConfig.maxZoom
        const val MAX_SCALE_FACTOR = 2

        var zoom = MIN_ZOOM
    }

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, Config.mapConfig.imageDrawableId)

    private lateinit var bitmapDrawable: BitmapDrawable

    override fun setSize(x: Int, y: Int) {
        super.setSize(x, y)

        bitmapDrawable = BitmapDrawable(context.resources, image)
        bitmapDrawable.tileModeX = Shader.TileMode.MIRROR
        bitmapDrawable.tileModeY = Shader.TileMode.MIRROR
    }

    override fun render(canvas: Canvas) {
        val translateX = (screenWidth / 2 - Player.posX / zoom).toInt()
        val translateY = (screenHeight / 2 - Player.posY / zoom).toInt()

        val src = Rect(0, 0, image.width, image.height)
        val dst = Rect(
            translateX,
            translateY,
            translateX + (image.width / zoom).toInt(),
            translateY + (image.height / zoom).toInt()
        )
        canvas.drawBitmap(bitmapDrawable.bitmap, src, dst, null)
    }

    // rescaling(2): https://en.wikipedia.org/wiki/Feature_scaling
    fun scale(scaleFactor: Float) {
        zoom = MIN_ZOOM + (scaleFactor - 1) * (MAX_ZOOM - MIN_ZOOM) / (MAX_SCALE_FACTOR - 1)
    }
}