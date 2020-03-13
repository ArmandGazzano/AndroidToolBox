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

        checkPermission()

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
    }

    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            makeContactRequest()
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            makeCameraRequest()
        }
    }

    private fun makeContactRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_CONTACTS),
            RECORD_REQUEST_CODE)
    }

    private fun makeCameraRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.CAMERA),
            RECORD_REQUEST_CODE)
    }
}
