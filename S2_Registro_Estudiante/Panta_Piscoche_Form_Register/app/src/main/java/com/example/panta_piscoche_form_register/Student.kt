import android.os.Parcel
import android.os.Parcelable

data class Student(
    val codigo: String,
    val apellidos: String,
    val nombres: String,
    val correo: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(codigo)
        parcel.writeString(apellidos)
        parcel.writeString(nombres)
        parcel.writeString(correo)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }
}