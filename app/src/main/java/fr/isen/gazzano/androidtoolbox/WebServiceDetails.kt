package fr.isen.gazzano.androidtoolbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class WebServiceDetails() :
    AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_service_details)

        val pokemon = intent.getIntExtra("pokemon_id", 0)
        Toast.makeText(this, "$pokemon", Toast.LENGTH_SHORT)
    }
}
