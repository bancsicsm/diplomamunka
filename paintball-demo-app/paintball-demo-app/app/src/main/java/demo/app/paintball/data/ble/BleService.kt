package demo.app.paintball.data.ble

import android.bluetooth.BluetoothDevice
import demo.app.paintball.data.ble.data.BleNetworkInfo

interface BleService {

    val bleNetworkInfo: BleNetworkInfo

    fun addListener(listener: BleServiceImpl.BleServiceListener): Boolean

    fun removeListener(listener: BleServiceImpl.BleServiceListener): Boolean

    fun connectDevice(tag: BluetoothDevice)

    fun disconnectDevice()

    fun startPositionSending()

    fun stopPositionSending()
}