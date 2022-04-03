package com.appgame.prestador.model.payment

import android.os.Parcel
import android.os.Parcelable


data class Payment(
    var paymentId: String,
    var user: String,
    var loanId: String,
    var amount: String,
    var date: String,
    var isAccepted: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(paymentId)
        parcel.writeString(user)
        parcel.writeString(loanId)
        parcel.writeString(amount)
        parcel.writeString(date)
        parcel.writeByte(if (isAccepted) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Payment> {
        override fun createFromParcel(parcel: Parcel): Payment {
            return Payment(parcel)
        }

        override fun newArray(size: Int): Array<Payment?> {
            return arrayOfNulls(size)
        }
    }

}