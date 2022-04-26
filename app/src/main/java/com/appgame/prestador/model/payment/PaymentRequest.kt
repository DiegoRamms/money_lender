package com.appgame.prestador.model.payment

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("paymentId")
    private val paymentId: String
)