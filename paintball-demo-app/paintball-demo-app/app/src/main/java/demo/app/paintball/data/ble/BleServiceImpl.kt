package demo.app.paintball.data.ble

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattService
import demo.app.paintball.PaintballApplication.Companion.context
import demo.app.paintball.data.ble.data.BleNetworkInfo
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.util.toast
import no.nordicsemi.android.ble.BleManager
import no.nordicsemi.android.ble.observer.ConnectionObserver
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.experimental.and

@Singleton
class BleServiceImpl @Inject constructor() : BleService, BleManager(context), ConnectionObserver {

    companion object {
        val GATT_RANGING_SERVICE_UUID: UUID = UUID.fromString("f45a1000-00a6-413e-87db-580f0cab9adc")
        val GATT_RANGING_SERVICE_INFO_UUID: UUID = UUID.fromString("f45a1001-00a6-413e-87db-580f0cab9adc")
        val GATT_RANGING_SERVICE_RANGING_UUID: UUID = UUID.fromString("f45a1002-00a6-413e-87db-580f0cab9adc")
        val GATT_RANGING_SERVICE_MODE_UUID: UUID = UUID.fromString("f45a1003-00a6-413e-87db-580f0cab9adc")

        const val TAG_RANGING: Byte = 0x01
        const val TAG_POWERDOWN: Byte = 0x00
    }

    private var rangingService: BluetoothGattService? = null
    private var infoCharacteristic: BluetoothGattCharacteristic? = null
    private var rangingCharacteristic: BluetoothGattCharacteristic? = null
    private var modeCharacteristic: BluetoothGattCharacteristic? = null

    private var bleServiceListeners = LinkedList<BleServiceListener>()

    override var bleNetworkInfo = BleNetworkInfo()
        private set

    init {
        setConnectionObserver(this)
    }

    override fun addListener(listener: BleServiceListener) = bleServiceListeners.add(listener)

    override fun removeListener(listener: BleServiceListener) = bleServiceListeners.remove(listener)

    override fun connectDevice(tag: BluetoothDevice) {
        connect(tag)
            .useAutoConnect(true)
            .timeout(100000)
            .retry(3, 100)
            .enqueue()
    }

    override fun disconnectDevice() {
        disconnect()
    }

    override fun startPositionSending() {
        writeCharacteristic(modeCharacteristic, byteArrayOf(TAG_RANGING))
            .fail { _, status -> toast("Failed to start sending positions, status: $status") }
            .enqueue()
    }

    override fun stopPositionSending() {
        writeCharacteristic(modeCharacteristic, byteArrayOf(TAG_POWERDOWN))
            .fail { _, status -> toast("Failed to stop sending positions, status: $status") }
            .enqueue()
    }

    private fun updateBleNetworkInfo() {
        readCharacteristic(infoCharacteristic).with { _, data ->
            data.value?.let {
                val bb = ByteBuffer.wrap(it)
                bb.order(ByteOrder.LITTLE_ENDIAN)
                bleNetworkInfo.apply {
                    groupId = bb.short and 0xFFFF.toShort()
                    tagId = bb.short and 0xFFFF.toShort()
                    anchorCount = bb.short
                    tagCount = bb.short
                }
            }
        }.enqueue()
    }

    override fun getGattCallback() = BleGattCallback() as BleManagerGattCallback

    override fun onDeviceFailedToConnect(device: BluetoothDevice, reason: Int) {
        bleServiceListeners.forEach { it.onBleDisconnected(this) }
    }

    override fun onDeviceReady(device: BluetoothDevice) {
        updateBleNetworkInfo()
        bleServiceListeners.forEach { it.onBleConnected(this) }
    }

    override fun onDeviceDisconnected(device: BluetoothDevice, reason: Int) {
        bleServiceListeners.forEach { it.onBleDisconnected(this) }
    }

    override fun onDeviceConnecting(device: BluetoothDevice) {}
    override fun onDeviceConnected(device: BluetoothDevice) {}
    override fun onDeviceDisconnecting(device: BluetoothDevice) {}

    private inner class BleGattCallback : BleManagerGattCallback() {
        override fun isRequiredServiceSupported(gatt: BluetoothGatt): Boolean {
            rangingService = gatt.getService(GATT_RANGING_SERVICE_UUID) ?: return false
            infoCharacteristic = rangingService?.getCharacteristic(GATT_RANGING_SERVICE_INFO_UUID) ?: return false
            rangingCharacteristic = rangingService?.getCharacteristic(GATT_RANGING_SERVICE_RANGING_UUID) ?: return false
            modeCharacteristic = rangingService?.getCharacteristic(GATT_RANGING_SERVICE_MODE_UUID) ?: return false
            return true
        }

        override fun initialize() {
            super@BleServiceImpl.beginAtomicRequestQueue()
                .add(requestMtu(247))
                .add(enableNotifications(rangingCharacteristic))
                .enqueue()

            setNotificationCallback(rangingCharacteristic).with { _, data ->
                if (modeCharacteristic?.value?.get(0) == TAG_RANGING) {
                    data.value?.let { dataValue ->
                        val positionData = BlePositionData.parse(dataValue, bleNetworkInfo.anchorCount)
                        bleServiceListeners.forEach {
                            it.onBlePositionDataReceived(this@BleServiceImpl, positionData)
                        }
                    }
                }
            }
        }

        override fun onDeviceDisconnected() {
            rangingService = null
        }
    }

    interface BleServiceListener {
        fun onBleConnected(connection: BleService)
        fun onBlePositionDataReceived(connection: BleService, data: BlePositionData)
        fun onBleDisconnected(connection: BleService)
    }
}