package com.appgame.prestador.domain.loan

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoansResponse constructor(
    @SerializedName("data")
    val loans: List<LoanDTO>
    ) : BaseResponse() {
}