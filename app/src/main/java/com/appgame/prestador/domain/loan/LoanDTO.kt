package com.appgame.prestador.domain.loan


import com.google.gson.annotations.SerializedName

data class LoanDTO(
    @SerializedName("_id")
    val idLoan: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("dateLimit")
    val dateLimit: String,
    @SerializedName("dateStart")
    val dateStart: String,
    @SerializedName("interestPercent")
    val interestPercent: String,
    @SerializedName("paymentsTime")
    val paymentsTime: String,
    @SerializedName("userBorrower")
    val userBorrower: String,
    @SerializedName("userMoneyLender")
    val userMoneyLender: String
)