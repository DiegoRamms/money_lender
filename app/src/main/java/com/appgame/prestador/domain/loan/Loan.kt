package com.appgame.prestador.domain.loan


import android.os.Parcel
import android.os.Parcelable

data class Loan(
    val loanId: String,
    val amount: String,
    val dateLimit: String,
    val dateStart: String,
    val interestPercent: String,
    val paymentsTime: String,
    val interestTime: String,
    val userBorrower: String,
    val userMoneyLender: String,
    val status: String,
    val type: String,
    val comment: String
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
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(loanId)
        parcel.writeString(amount)
        parcel.writeString(dateLimit)
        parcel.writeString(dateStart)
        parcel.writeString(interestPercent)
        parcel.writeString(paymentsTime)
        parcel.writeString(interestTime)
        parcel.writeString(userBorrower)
        parcel.writeString(userMoneyLender)
        parcel.writeString(status)
        parcel.writeString(type)
        parcel.writeString(comment)
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