package demo.app.paintball.map.rendering

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import demo.app.paintball.map.MapView
import demo.app.paintball.util.xToMapPx
import demo.app.paintball.util.yToMapPx

class MapViewImpl : SurfaceView, MapView {

    private var renderLoop: RenderLoop? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // empty
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                var retry = true
                renderLoop?.running = false
                while (retry) {
                    try {
                        renderLoop?.join()
                        retry = false
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                val loop = RenderLoop(this@MapViewImpl, width, height)
                loop.running = true
                loop.start()
                renderLoop = loop
                (context as MapViewCreatedListener).mapViewCreated()
            }
        })
    }

    override fun setPlayerPosition(posX: Int, posY: Int) {
        renderLoop?.setPlayerPosition(posX.xToMapPx(), posY.yToMapPx())
    }

    override fun setMovablePosition(playerName: String, posX: Int, posY: Int) {
        renderLoop?.setMovablePosition(playerName, posX.xToMapPx(), posY.yToMapPx())
    }

    override fun setPlayerOrientation(degree: Float) {
        renderLoop?.setPlayerOrientation(degree)
    }

    override fun addRedPlayer(playerName: String) {
        renderLoop?.addRedPlayer(playerName)
    }

    override fun addBluePlayer(playerName: String) {
        renderLoop?.addBluePlayer(playerName)
    }

    override fun zoom(scaleFactor: Float) {
        renderLoop?.zoom(scaleFactor)
    }

    override fun addAnchor(posX: Int, posY: Int) {
        renderLoop?.addAnchor(posX.xToMapPx(), posY.yToMapPx())
    }

    interface MapViewCreatedListener {
        fun mapViewCreated()
    }
}