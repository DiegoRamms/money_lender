package com.appgame.prestador.model.payment

import com.google.gson.annotations.SerializedName
import java.util.*

data class CreatePaymentRequest(
    @SerializedName("loanId")
    val loanId: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("date")
    val date: Date

)