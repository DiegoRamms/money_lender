package com.appgame.prestador.domain.payment

import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName
    val paymentDTO: PaymentDTO
) :{
}