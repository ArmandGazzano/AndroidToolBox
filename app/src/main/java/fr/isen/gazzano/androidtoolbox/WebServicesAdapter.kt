package fr.isen.gazzano.androidtoolbox

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_permissions_cell.view.*
import kotlinx.android.synthetic.main.activity_web_services_cell.view.*
import java.util.*
import kotlin.collections.ArrayList

class WebServicesAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WebServicesAdapter.WebServicesViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: WebServicesAdapter.WebServicesViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class WebServicesViewHolder(userView: View) : RecyclerView.ViewHolder(userView) {
        val contactName: TextView = userView.user_name
    }

}

class User{

}
