package com.appgame.prestador.domain.payment

import android.os.Parcel
import android.os.Parcelable

data class LoanPaymentDetail(
    val progressPayPercentage: Double,
    val progressPayText: String,
    val nextPayMoney: String,
    val nextPayTime: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(progressPayPercentage)
        parcel.writeString(progressPayText)
        parcel.writeString(nextPayMoney)
        parcel.writeString(nextPayTime)
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