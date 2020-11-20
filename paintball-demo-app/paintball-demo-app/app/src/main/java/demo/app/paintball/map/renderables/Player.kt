package demo.app.paintball.map.renderables

import android.graphics.*
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.R
import demo.app.paintball.config.Config
import demo.app.paintball.util.xToMapPx
import demo.app.paintball.util.yToMapPx


class Player : Renderable() {

    companion object {
        const val size = 3
        const val phoneOrientation = 90.0F  // east

        var posX = 1800.xToMapPx()
        var posY = 1500.yToMapPx()
    }

    private var screenCenterX = 0F
    private var screenCenterY = 0F

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ic_player_arrow)

    private val matrix = Matrix()

    override fun setSize(x: Int, y: Int) {
        super.setSize(x, y)

        screenCenterX = (x / 2).toFloat()
        screenCenterY = (y / 2).toFloat()

        setOrientation(0F)
    }

    override fun render(canvas: Canvas) {
        canvas.drawBitmap(image, matrix, null)
    }

    fun setOrientation(degree: Float) {
        val translateX = screenCenterX - (image.width / size / 2)
        val translateY = screenCenterY - (image.height / size / 2)

        val src = RectF(0F, 0F, image.width.toFloat(), image.height.toFloat())
        val dst = RectF(
            translateX,
            translateY,
            translateX + image.width / size,
            translateY + image.height / size
        )
        matrix.setRectToRect(src, dst, Matrix.ScaleToFit.CENTER)

        val mapOrientation = Config.mapConfig.mapOrientation.toFloat()
        val phoneDegree = (degree + phoneOrientation) % 360.0F
        val mapDegree = (phoneDegree - mapOrientation) % 360.0F
        matrix.postRotate(
            mapDegree,
            translateX + (image.width / 2) / size,
            translateY + (image.height / 2) / size
        )
    }
}