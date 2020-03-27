package fr.isen.gazzano.androidtoolbox

import android.bluetooth.BluetoothGattCharacteristic
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder
import kotlinx.android.synthetic.main.bluetooth_characteristic_cell.view.*
import kotlinx.android.synthetic.main.bluetooth_details_cell.view.*

class BluetoothDetailsAdapter(val serviceList: MutableList<BLEService>) :
    ExpandableRecyclerViewAdapter<BluetoothDetailsAdapter.ServicesViewHolder, BluetoothDetailsAdapter.CharacteristicViewHolder>(serviceList) {

    class ServicesViewHolder(detailsView: View) : GroupViewHolder(detailsView){
        val arrow: ImageView = detailsView.arrow
        val serviceName: TextView = detailsView.textchelou
    }

    class CharacteristicViewHolder(itemView: View): ChildViewHolder(itemView){
        val characteristicUUID: TextView = itemView.characteristicUUID
    }

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder =
        ServicesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bluetooth_details_cell, parent, false)
        )

    override fun getItemCount(): Int = serviceList.size

    override fun onCreateChildViewHolder(
        parent: ViewGroup?,
        viewType: Int
    ): CharacteristicViewHolder =
        CharacteristicViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.bluetooth_characteristic_cell, parent, false)
        )

    override fun onBindChildViewHolder(
        holder: CharacteristicViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>,
        childIndex: Int
    ) {
        val characteristic: BluetoothGattCharacteristic = (group as BLEService).items[childIndex]
        val uuid = characteristic.uuid
        holder.characteristicUUID.text = uuid.toString()
    }

    override fun onBindGroupViewHolder(
        holder: ServicesViewHolder,
        flatPosition: Int,
        group: ExpandableGroup<*>
    ) {
        val title = group.title
    }
}