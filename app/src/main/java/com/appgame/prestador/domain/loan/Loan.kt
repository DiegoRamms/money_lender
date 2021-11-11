package com.appgame.prestador.domain.loan


import android.os.Parcel
import android.os.Parcelable

data class Loan(
    val idLoan: String,
    val amount: String,
    val dateLimit: String,
    val dateStart: String,
    val interestPercent: String,
    val paymentsTime: String,
    val userBorrower: String,
    val userMoneyLender: String,
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idLoan)
        parcel.writeString(amount)
        parcel.writeString(dateLimit)
        parcel.writeString(dateStart)
        parcel.writeString(interestPercent)
        parcel.writeString(paymentsTime)
        parcel.writeString(userBorrower)
        parcel.writeString(userMoneyLender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Loan> {
        override fun createFromParcel(parcel: Parcel): Loan {
            return Loan(parcel)
        }

        override fun newArray(size: Int): Array<Loan?> {
            return arrayOfNulls(size)
        }
    }
}