package fr.isen.gazzano.androidtoolbox

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bluetooth.*


class BluetoothActivity : AppCompatActivity() {

    /*
    companion object {
        private val REQUEST_ENABLE_BT = 1
        private val SCAN_PERIOD: Long = 10000
    }

    private val deviceList = mutableListOf<Device>()
    private val addressList = mutableListOf<String>()

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val isBLEEnable: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    private lateinit var handler: Handler
    private var mScanning: Boolean = false
    private lateinit var adapter: BluetoothActivityAdapter
    private val devices = ArrayList<ScanResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        searchButton.setOnClickListener {
            when {
                isBLEEnable -> initScan()
                bluetoothAdapter != null -> {
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                }
                else -> {
                    bleTextFailed.visibility = View.VISIBLE
                }
            }
        }
        deviceListRV.adapter = BluetoothActivityAdapter(deviceList)
        deviceListRV.layoutManager = LinearLayoutManager(this)
    }

    override fun onStop() {
        super.onStop()
        if (isBLEEnable) {
            scanLeDevice(false)
        }
    }

    private fun initScan() {
        progressBar.visibility = View.VISIBLE
        dividerBle.visibility = View.GONE

        handler = Handler()
        scanLeDevice(true)
    }

    private fun scanLeDevice(enable: Boolean) {
        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if (enable) {
                handler.postDelayed({
                    mScanning = false
                    stopScan(leScanCallBack)
                }, SCAN_PERIOD)
                mScanning = true
                startScan(leScanCallBack)
                adapter.notifyDataSetChanged()
            } else {
                mScanning = false
                stopScan(leScanCallBack)
            }
        }
    }

    private val leScanCallBack = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.w("BLE", "${result.device}")

            Log.w("BLE", "${result.device}")
            runOnUiThread {
                adapter.addDeviceToList(result)
                adapter.notifyDataSetChanged()
                dividerBle.visibility = View.GONE
            }
        }
    }

    private fun initBLEScan() {
        adapter = BluetoothActivityAdapter(
            arrayListOf()
        )
        deviceListRV.adapter = adapter
        deviceListRV.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        handler = Handler()

        scanLeDevice(true)
        searchButton.setOnClickListener{
            scanLeDevice(!mScanning)
        }

    }

    /*
    private fun addDeviceToList(result: ScanResult){
        if (deviceList.size == 0){
            deviceList += Device(result.device.name, result.device.address, result.rssi)
            addressList += result.device.address
        } else {
            if (addressList.contains(result.device.address)) {
                //update
            } else {
                deviceList += Device(result.device.name, result.device.address, result.rssi)
                addressList += result.device.address
            }
        }

        Log.w("BLE", "$deviceList")
    }
     */

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                if (isBLEEnable) {
                    Toast.makeText(this, "Bluetooth has been enabled", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Bluetooth has been disabled", Toast.LENGTH_SHORT).show()
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Bluetooth enabling has been canceled", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}*/
    private lateinit var handler: Handler
    private var mScanning: Boolean = false
    private lateinit var adapter: BluetoothActivityAdapter
    private val devices = ArrayList<ScanResult>()

    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private val isBLEEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth)

        bleTextFailed.visibility = View.GONE

        deviceListRV.adapter = BluetoothActivityAdapter(devices, ::onDeviceClicked)
        deviceListRV.layoutManager = LinearLayoutManager(this)

        searchButton.setOnClickListener {
            when {
                isBLEEnabled -> {
                    //init scan
                    initBLEScan()
                    initScan()
                }
                bluetoothAdapter != null -> {
                    //ask for permission
                    val enableBTIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBTIntent, REQUEST_ENABLE_BT)
                }
                else -> {
                    //device is not compatible with your device
                    bleTextFailed.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initScan() {
        progressBar.visibility = View.VISIBLE
        dividerBle.visibility = View.GONE

        handler = Handler()

        scanLeDevice(true)
    }

    private fun scanLeDevice(enable: Boolean) {
        bluetoothAdapter?.bluetoothLeScanner?.apply {
            if (enable) {
                Log.w("BLEScanActivity", "Scanning for devices")
                handler.postDelayed({
                    mScanning = false
                    stopScan(leScanCallback)
                }, SCAN_PERIOD)
                mScanning = true
                startScan(leScanCallback)
                adapter.clearResults()
                adapter.notifyDataSetChanged()
            } else {
                mScanning = false
                stopScan(leScanCallback)
            }
        }
    }

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            Log.w("BLE", "${result.device}")
            runOnUiThread {
                adapter.addDeviceToList(result)
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun initBLEScan() {
        adapter = BluetoothActivityAdapter(
            arrayListOf(),
            ::onDeviceClicked
        )
        deviceListRV.adapter = adapter
        deviceListRV.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        handler = Handler()

        scanLeDevice(true)
        deviceListRV.setOnClickListener{
            scanLeDevice(!mScanning)
        }

    }

    private fun onDeviceClicked(device: BluetoothDevice) {
        val intent = Intent(this, BluetoothActivityAdapter::class.java)
        intent.putExtra("ble_device", device)
        startActivity(intent)
    }

    companion object {
        private const val SCAN_PERIOD: Long = 10000
        private const val REQUEST_ENABLE_BT = 44
    }
}