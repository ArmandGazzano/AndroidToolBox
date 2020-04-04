import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import org.json.JSONArray

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

@Parcelize
data class Pokemon(

	@SerializedName("id") val id: Int,
	@SerializedName("num") val num: Int,
	@SerializedName("name") val name: String,
	@SerializedName("img") val img: String,
	@SerializedName("type") val type: JSONArray,
	@SerializedName("height") val height: String,
	@SerializedName("weight") val weight: String,
	@SerializedName("candy") val candy: String,
	//@SerializedName("candy_count") val candy_count : Int,
	@SerializedName("egg") val egg: String,
	@SerializedName("spawn_chance") val spawn_chance: Double,
	@SerializedName("avg_spawns") val avg_spawns: Int,
	@SerializedName("spawn_time") val spawn_time: String,
	@SerializedName("multipliers") val multipliers: List<Double>,
	@SerializedName("weaknesses") val weaknesses: JSONArray,
	@SerializedName("next_evolution") val next_evolution: List<Next_evolution>
) : Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readInt(),
		parcel.readInt(),
		parcel.readString().toString(),
		parcel.readString().toString(),
		TODO("type"),
		parcel.readString().toString(),
		parcel.readString().toString(),
		parcel.readString().toString(),
		parcel.readString().toString(),
		parcel.readDouble(),
		parcel.readInt(),
		parcel.readString().toString(),
		TODO("multipliers"),
		TODO("weaknesses"),
		TODO("next_evolution")
	) {
	}

	companion object : Parceler<Pokemon> {

		override fun Pokemon.write(parcel: Parcel, flags: Int) {
			parcel.writeInt(id)
			parcel.writeInt(num)
			parcel.writeString(name)
			parcel.writeString(img)
			parcel.writeString(height)
			parcel.writeString(weight)
			parcel.writeString(candy)
			parcel.writeString(egg)
			parcel.writeDouble(spawn_chance)
			parcel.writeInt(avg_spawns)
			parcel.writeString(spawn_time)
			//parcel.writeStringList(weaknesses)
		}

		override fun create(parcel: Parcel): Pokemon {
			return Pokemon(parcel)
		}
	}
}