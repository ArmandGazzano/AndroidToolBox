package fr.isen.gazzano.androidtoolbox


import android.bluetooth.*
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bluetooth_details.*

class BluetoothDetails : AppCompatActivity() {

    private var bluetoothGatt: BluetoothGatt? = null
    private var TAG: String = "services"
    private lateinit var adapter: BluetoothDetailsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bluetooth_details)

        val device: BluetoothDevice = intent.getParcelableExtra("ble_device")
        bluetoothGatt = device.connectGatt(this, true, gattCallback)
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(
            gatt: BluetoothGatt,
            status: Int,
            newState: Int
        ) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    runOnUiThread {
                        connectionState.text = STATE_CONNECTED
                        //name.text = device?.name
                    }
                    bluetoothGatt?.discoverServices()
                    Log.i(TAG, "Connected to GATT")
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    runOnUiThread {
                        connectionState.text = STATE_DISCONNECTED
                    }
                    Log.i(TAG, "Disconnected from GATT")
                }
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            runOnUiThread {
                detailsView.adapter = BluetoothDetailsAdapter(
                    gatt?.services?.map {
                        BLEService(
                            it.uuid.toString(),
                            it.characteristics
                        )
                    }?.toMutableList() ?: arrayListOf()
                )
                detailsView.layoutManager = LinearLayoutManager(this@BluetoothDetails)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        bluetoothGatt?.close()
    }

    companion object {
        private const val STATE_DISCONNECTED = "déconnecté"
        private const val STATE_CONNECTING = 1
        private const val STATE_CONNECTED = "Connecté"
        const val ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED"
        const val ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED"
        const val ACTION_GATT_SERVICES_DISCOVERED =
            "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED"
        const val ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE"
        const val EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA"
    }
}