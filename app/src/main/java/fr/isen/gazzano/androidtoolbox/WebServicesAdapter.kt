package fr.isen.gazzano.androidtoolbox

import Pokemon
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*
import java.text.DecimalFormat

class WebServicesAdapter(internal var context: Context, val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WebServicesViewHolder = WebServicesViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.activity_web_services_cell, parent, false))

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: WebServicesViewHolder, position: Int) {
        holder.pokemonName.text = pokemonList[position].name

        val nf = DecimalFormat("000")
        var num = nf.format(pokemonList[position].num)
        Picasso.get().load("https://www.serebii.net/pokemongo/pokemon/$num.png").into(holder.pokemonPhoto)

        holder.pokemonId.text = "Id: " + pokemonList[position].num.toString() + "   Taille : " + pokemonList[position].height.toString() + "    Poids : " + pokemonList[position].weight.toString()
        holder.pokemonTypes.text = pokemonList[position].type.toString()

    }

    class WebServicesViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val pokemonName: TextView = userView.user_name
        val pokemonPhoto: ImageView = userView.user_photo
        val pokemonId : TextView = userView.user_address
        val pokemonTypes : TextView = userView.user_email
    }
}
