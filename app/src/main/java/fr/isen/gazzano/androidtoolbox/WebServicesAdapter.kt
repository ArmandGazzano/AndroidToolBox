package fr.isen.gazzano.androidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*

class WebServicesAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WebServicesViewHolder = WebServicesAdapter.WebServicesViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.activity_web_services_cell, parent, false))

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: WebServicesAdapter.WebServicesViewHolder, position: Int) {
        holder.user_name.text = userList[position].toString()
    }

    class WebServicesViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val user_name: TextView = userView.user_name

    }

}

data class User(var name: String)
