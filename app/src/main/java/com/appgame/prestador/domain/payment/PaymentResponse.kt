package com.appgame.prestador.domain.payment

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("data")
    val paymentDTO: PaymentDTO
) : BaseResponse()