package com.appgame.prestador.domain.loan

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoanResponse(
    @SerializedName("data")
    val loanDTO: LoanDTO
): BaseResponse()