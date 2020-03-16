package fr.isen.gazzano.androidtoolbox

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_web_services.*
import kotlinx.android.synthetic.main.activity_web_services_cell.*
import org.json.JSONArray
import org.json.JSONObject


class WebServicesActivity : AppCompatActivity(){

    private val url = "https://randomuser.me/api/?results=10&?nat=fr"
    private val user: MutableList<User> = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        Volley.newRequestQueue(this).add(getJsonObjectRequest())
        recyclerView.adapter = WebServicesAdapter(user)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

     fun getJsonObjectRequest() : JsonObjectRequest {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                parseObject(response)
            },
            Response.ErrorListener {  })

        Log.d("User", user.toString())
        return jsonObjectRequest
    }

    fun parseObject(response: JSONObject) {
        val jsonArrayResults : JSONArray = response.getJSONArray("results")
        val size: Int = jsonArrayResults.length()
        val i: Int = 0
        for (i in 0 until size){
            val userObject = jsonArrayResults.getJSONObject(i)
            val nameObject = userObject.getJSONObject("name")
            val name = nameObject.getString("first")
            this.user += User(name)
            this.user.add(User(name = name))
            val gender = userObject.getString("gender")

            Log.d("JSON", "$name $gender")
        }
    }
}
