package demo.app.paintball.map

import android.view.View

interface MapView {

    fun setPlayerPosition(posX: Int, posY: Int)

    fun setPlayerOrientation(degree: Float)

    fun setMovablePosition(playerName: String, posX: Int, posY: Int)

    fun addRedPlayer(playerName: String)

    fun addBluePlayer(playerName: String)

    fun zoom(scaleFactor: Float)

    fun addAnchor(posX: Int, posY: Int)

    fun setOnTouchListener(listener: View.OnTouchListener)
}