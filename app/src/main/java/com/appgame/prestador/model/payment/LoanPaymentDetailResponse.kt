package com.appgame.prestador.model.payment

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoanPaymentDetailResponse(
    @SerializedName("data")
    val loanPaymentDetailDTO: LoanPaymentDetailDTO
): BaseResponse()