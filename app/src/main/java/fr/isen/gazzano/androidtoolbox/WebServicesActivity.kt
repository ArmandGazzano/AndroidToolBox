package fr.isen.gazzano.androidtoolbox

import Next_evolution
import Pokemon
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_web_services.*
import org.json.JSONArray
import org.json.JSONObject


class WebServicesActivity : AppCompatActivity() {

    private val url =
        "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json"
    private val listPokemon = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_services)

        Volley.newRequestQueue(this).add(getJsonObjectRequest())

        //val pokemonList2 =  Gson().fromJson(, Pokemon::class.java)
    }

    fun getJsonObjectRequest(): JsonObjectRequest {

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val pokemonListGson = Gson().fromJson(response.toString(), Pokemon::class.java)
                parseObject(response)
                println(pokemonListGson)
            },
            Response.ErrorListener { })

        return jsonObjectRequest
    }

    fun parseObject(response: JSONObject) {
        val jsonArrayResults: JSONArray = response.getJSONArray("pokemon")
        val size: Int = jsonArrayResults.length()
        val i: Int = 0
        for (i in 0 until size) {
            val pokemonObject = jsonArrayResults.getJSONObject(i)

            val pokemonTypeObject = pokemonObject.getJSONArray("type")
            val pokemonWeaknessesObject = pokemonObject.getJSONArray("weaknesses")

            /*
            val pokemonWeaknessObjects = pokemonObject.getJSONArray("weaknesses")
            val pokemonNextEvolutionObjects = pokemonObject.getJSONArray("next_evolution")
             */

            val id = pokemonObject.getInt("id")
            val num = pokemonObject.getInt("num")
            val name = pokemonObject.getString("name")
            val img = pokemonObject.getString("img")
            val type = pokemonTypeObject   //pokemonTypeObject.getString("type")
            val height = pokemonObject.getString("height")
            val weight = pokemonObject.getString("weight")
            val candy = pokemonObject.getString("candy")
            //val candy_count = pokemonObject.getInt("candy_count")
            val egg = pokemonObject.getString("egg")
            val spawn_chance = pokemonObject.getDouble("spawn_chance")
            val avg_spawns = pokemonObject.getInt("avg_spawns")
            val spawn_time = pokemonObject.getString("spawn_time")
            val multipliers = listOf<Double>()
            val weaknesses = pokemonWeaknessesObject
            val next_evolution = listOf<Next_evolution>()

            listPokemon += Pokemon(
                id,
                num,
                name,
                img,
                type,
                height,
                weight,
                candy, /*candy_count,*/
                egg,
                spawn_chance,
                avg_spawns,
                spawn_time,
                multipliers,
                weaknesses,
                next_evolution
            )
        }
        recyclerView.adapter = WebServicesAdapter(this, listPokemon, ::onDeviceClicked)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun onDeviceClicked(pokemon: Pokemon) {
        val intent = Intent(this, WebServiceDetails::class.java)
        intent.putExtra("name", pokemon.name)
        intent.putExtra("id", pokemon.id)
        intent.putExtra("num",pokemon.num)
        intent.putExtra("img", pokemon.img)
        intent.putExtra("type1", pokemon.type.getString(0))
        if (pokemon.type.length() == 2) {
            intent.putExtra("type2", pokemon.type.getString(1))
        }
        intent.putExtra("height", pokemon.height)
        intent.putExtra("weight", pokemon.weight)
        intent.putExtra("candy", pokemon.candy)
        intent.putExtra("egg", pokemon.egg)
        intent.putExtra("spawn_chance", pokemon.spawn_chance)
        intent.putExtra("avg_spawns", pokemon.avg_spawns)
        intent.putExtra("spawn_time", pokemon.spawn_time)
        var faiblesse: MutableList<String>? = null
        for (a in 0 until pokemon.weaknesses.length()) {
            faiblesse?.add(pokemon.weaknesses.getString(a))
        }
        intent.putExtra("weaknesses", faiblesse.toString())
        startActivity(intent)
    }
}