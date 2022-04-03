package com.appgame.prestador.model.payment

import android.os.Parcel
import android.os.Parcelable

data class LoanPaymentDetail(
    val totalToPay: Double,
    val progressPayPercentage: Double,
    val progressPayText: String,
    val nextPayMoney: String,
    val nextPayTime: String,
    val isPaidOut: Boolean,
    val payments: List<Payment>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.createTypedArrayList(Payment)!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(totalToPay)
        parcel.writeDouble(progressPayPercentage)
        parcel.writeString(progressPayText)
        parcel.writeString(nextPayMoney)
        parcel.writeString(nextPayTime)
        parcel.writeByte(if (isPaidOut) 1 else 0)
        parcel.writeTypedList(payments)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoanPaymentDetail> {
        override fun createFromParcel(parcel: Parcel): LoanPaymentDetail {
            return LoanPaymentDetail(parcel)
        }

        override fun newArray(size: Int): Array<LoanPaymentDetail?> {
            return arrayOfNulls(size)
        }
    }


}