package com.appgame.prestador.model.payment

import com.google.gson.annotations.SerializedName
import java.util.*

data class LoanPaymentDetailDTO(
    @SerializedName("totalToPay")
    val totalToPay: Double,
    @SerializedName("progressPayPercentage")
    val progressPayPercentage: Double,
    @SerializedName("progressPayText")
    val progressPayText: String,
    @SerializedName("nextPayMoney")
    val nextPayMoney: String,
    @SerializedName("nextPayTime")
    val nextPayTime: Date,
    @SerializedName("isPaidOut")
    val isPaidOut: Boolean,
    @SerializedName("payments")
    var paymentsDTO: List<PaymentDTO>,
)