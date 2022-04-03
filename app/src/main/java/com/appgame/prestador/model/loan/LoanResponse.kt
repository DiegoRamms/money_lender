package com.appgame.prestador.model.loan

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoanResponse(
    @SerializedName("data")
    val loanDTO: LoanDTO
): BaseResponse()