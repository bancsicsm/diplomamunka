package demo.app.paintball.map.sensors

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.View
import android.view.View.OnTouchListener
import demo.app.paintball.PaintballApplication


class GestureSensor(val gestureListener: GestureListener) : OnTouchListener,
    OnScaleGestureListener, GestureDetector.SimpleOnGestureListener() {

    companion object {
        const val zoomLimit = 0.2F
        const val scrollLimit = 40.0F
    }

    private val scaleDetector: ScaleGestureDetector =
        ScaleGestureDetector(PaintballApplication.context, this)

    private val gestureDetector: GestureDetector =
        GestureDetector(PaintballApplication.context, this)

    private var scaleFactor = 1F
    private var lastScale = 1F

    private var up = false

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        scaleFactor *= detector.scaleFactor
        // Prevent our view from becoming too small, too big
        scaleFactor = if (scaleFactor < 1) 1F else scaleFactor
        scaleFactor = if (scaleFactor > 2) 2F else scaleFactor
        // Change precision to help with jitter when user just rests their fingers
        scaleFactor = (scaleFactor * 100).toInt().toFloat() / 100
        gestureListener.onScaleChanged(scaleFactor)
        val scaleDiff = scaleFactor - lastScale
        if (scaleDiff > zoomLimit) {
            gestureListener.onZoomIn()
            lastScale = scaleFactor
        } else if (scaleDiff < -zoomLimit) {
            gestureListener.onZoomOut()
            lastScale = scaleFactor
        }
        return true
    }

    interface GestureListener {
        fun onScaleChanged(scaleFactor: Float)
        fun onZoomIn()
        fun onZoomOut()
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(p0: MotionEvent?) {
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return false
    }
}