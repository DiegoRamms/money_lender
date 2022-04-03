package com.appgame.prestador.model.loan

import android.os.Parcel
import android.os.Parcelable

data class LoansDetail(
    val totalLoans: Int,
    val totalDebts: Int,
    val loans: List<Loan>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(Loan)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(totalLoans)
        parcel.writeInt(totalDebts)
        parcel.writeTypedList(loans)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoansDetail> {
        override fun createFromParcel(parcel: Parcel): LoansDetail {
            return LoansDetail(parcel)
        }

        override fun newArray(size: Int): Array<LoansDetail?> {
            return arrayOfNulls(size)
        }
    }
}