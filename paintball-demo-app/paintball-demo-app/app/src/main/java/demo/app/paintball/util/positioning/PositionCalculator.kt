package demo.app.paintball.util.positioning

import demo.app.paintball.data.ble.data.BlePositionData

interface PositionCalculator {

    var listener: PositionCalculatorListener

    fun calculate(data: BlePositionData)

    interface PositionCalculatorListener {
        fun onPositionCalculated(posX: Int, posY: Int)
    }
}