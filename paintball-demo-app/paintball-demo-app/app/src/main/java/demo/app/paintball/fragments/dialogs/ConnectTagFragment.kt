package demo.app.paintball.fragments.dialogs

import android.app.Activity
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import demo.app.paintball.PaintballApplication
import demo.app.paintball.R
import demo.app.paintball.data.ble.BleService
import demo.app.paintball.data.ble.BleServiceImpl
import demo.app.paintball.data.ble.data.BlePositionData
import demo.app.paintball.util.toast
import kotlinx.android.synthetic.main.fragment_connect_tag.*
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet
import kotlin.concurrent.timerTask

class ConnectTagFragment : DialogFragment(), BleServiceImpl.BleServiceListener {

    companion object {
        const val BLE_SCAN_PERIOD = 500L
    }

    @Inject
    lateinit var bleService: BleService

    private lateinit var listener: ConnectTagListener
    private lateinit var listAdapter: ArrayAdapter<String>
    private lateinit var bleScanner: BluetoothLeScanner

    private val timer = Timer()

    private var foundTags = HashSet<BluetoothDevice>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setStyle(STYLE_NORMAL, R.style.TitleDialog)

        bleService = PaintballApplication.services.ble().also { it.addListener(this@ConnectTagFragment) }

        try {
            listener = activity as ConnectTagListener
        } catch (e: ClassCastException) {
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_connect_tag, container, false)
        dialog?.setTitle(R.string.searching_tags)

        listAdapter = ArrayAdapter(
            PaintballApplication.context,
            R.layout.list_item_ble_devices,
            R.id.tvDeviceName,
            foundTags.map { it.name }
        )
        val lsAvailableDevices = view.findViewById<ListView>(R.id.lsAvailableDevices)
        lsAvailableDevices.adapter = listAdapter
        lsAvailableDevices.setOnItemClickListener { _, _, position, _ ->
            val foundTag = foundTags.elementAt(position)
            dialog?.setTitle(getString(R.string.connecting_to_tag, foundTag.name))
            progressBar.visibility = View.VISIBLE
            lsAvailableDevices.visibility = View.GONE
            bleService.connectDevice(foundTag)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scanTags()
    }

    private fun scanTags() {
        val bleManager = (listener as Activity).getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bleScanner = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bleManager.adapter.bluetoothLeScanner
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        bleScanner.startScan(scanning)
        progressBar.visibility = View.VISIBLE
        timer.schedule(timerTask {
            (listener as Activity).runOnUiThread {
                if (foundTags.isNotEmpty()) {
                    stopScan()
                    timer.cancel()
                    dialog?.setTitle(R.string.select_your_tag)
                }
            }
        }, 0L, BLE_SCAN_PERIOD)
    }

    private fun stopScan() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bleScanner.stopScan(scanning)
        }
        listAdapter.addAll(foundTags.map { it.name })
        progressBar.visibility = View.GONE
    }

    private val scanning: ScanCallback = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.device?.name?.let {
                foundTags.add(result.device)
            }
        }
    }

    override fun onBleConnected(connection: BleService) {
        toast("Tag connected")
        progressBar.visibility = View.GONE
        listener.onTagConnected()
        this.dismiss()
    }

    override fun onBlePositionDataReceived(connection: BleService, data: BlePositionData) {
    }

    override fun onBleDisconnected(connection: BleService) {
        toast("Tag disconnected")
    }

    override fun onDetach() {
        super.onDetach()
        timer.cancel()
        bleService.removeListener(this)
    }

    interface ConnectTagListener {
        fun onTagConnected()
    }
}