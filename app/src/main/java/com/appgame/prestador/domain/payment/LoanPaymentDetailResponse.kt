package com.appgame.prestador.domain.payment

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoanPaymentDetailResponse(
    @SerializedName("data")
    val loanPaymentDetailDTO: LoanPaymentDetailDTO
): BaseResponse()