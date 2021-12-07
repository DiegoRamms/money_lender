package com.appgame.prestador.domain.loan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class LoanDTO(
    @SerializedName("_id")
    val loanId: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("dateLimit")
    val dateLimit: Date,
    @SerializedName("dateStart")
    val dateStart: Date,
    @SerializedName("interestPercent")
    val interestPercent: String,
    @SerializedName("interestTime")
    @Expose
    val interestTime: String,
    @SerializedName("paymentsTime")
    val paymentsTime: String,
    @SerializedName("userBorrower")
    val userBorrower: String,
    @SerializedName("userMoneyLender")
    val userMoneyLender: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("comment")
    val comment: String
)