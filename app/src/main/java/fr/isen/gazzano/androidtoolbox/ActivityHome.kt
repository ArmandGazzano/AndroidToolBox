package fr.isen.gazzano.androidtoolbox

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*

class ActivityHome : AppCompatActivity() {

    var RECORD_REQUEST_CODE = 1
    private val USER_PREFS = "user_prefs"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE)

        lifeCycleButton.setOnClickListener {
            val intent = Intent(this, lifeCycleActivity::class.java)
            startActivity(intent)
            //Toast.makeText(applicationContext,"Identification réussi !",Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            val intent = Intent(this, SaveActivity::class.java)
            startActivity(intent)
        }

        decoButton.setOnClickListener {
            Toast.makeText(applicationContext, "Déconnexion réussi !", Toast.LENGTH_SHORT).show()
            this.finish()
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
        }

        permissionsButton.setOnClickListener {
            val intent = Intent(this, PermissionsActivity::class.java)
            startActivity(intent)
        }

        wsButton.setOnClickListener {
            val intent = Intent(this, WebServicesActivity::class.java)
            startActivity(intent)
        }

        bluetoothButton.setOnClickListener {
            val intent = Intent(this, BluetoothActivity::class.java)
            startActivity(intent)
        }
    }
}
