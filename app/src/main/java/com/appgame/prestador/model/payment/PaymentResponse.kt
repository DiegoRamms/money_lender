package com.appgame.prestador.model.payment

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("data")
    val paymentDTO: PaymentDTO
) : BaseResponse()