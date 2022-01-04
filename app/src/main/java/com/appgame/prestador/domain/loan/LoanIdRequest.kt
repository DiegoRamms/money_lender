package com.appgame.prestador.domain.loan

import com.google.gson.annotations.SerializedName

data class LoanIdRequest(
    @SerializedName("loanId")
    val loanId: String
)