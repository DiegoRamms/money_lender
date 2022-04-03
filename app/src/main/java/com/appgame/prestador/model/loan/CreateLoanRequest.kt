package com.appgame.prestador.model.loan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class CreateLoanRequest(
    @SerializedName("contactId")
    @Expose
    val contactId: String,
    @SerializedName("amount")
    @Expose
    val amount: String,
    @SerializedName("dateStart")
    @Expose
    val dateStart: Date?,
    @SerializedName("dateLimit")
    @Expose
    val dateLimit: Date?,
    @SerializedName("paymentsTime")
    @Expose
    val paymentsTime: String,
    @SerializedName("interestPercent")
    @Expose
    val interestPercent: String,
    @SerializedName("interestTime")
    @Expose
    val interestTime: String,
    @SerializedName("comment")
    @Expose
    val comment: String
)