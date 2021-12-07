package com.appgame.prestador.domain.contact

import android.os.Parcel
import android.os.Parcelable

data class Contact(
    val userId: String,
    val code: String,
    val email: String,
    val name: String,
    val contactId: String? = null
):  Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(code)
        parcel.writeString(email)
        parcel.writeString(name)
        parcel.writeString(contactId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Contact> {
        override fun createFromParcel(parcel: Parcel): Contact {
            return Contact(parcel)
        }

        override fun newArray(size: Int): Array<Contact?> {
            return arrayOfNulls(size)
        }
    }
}