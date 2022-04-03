package com.appgame.prestador.model.loan

import com.google.gson.annotations.SerializedName

data class LoanIdRequest(
    @SerializedName("loanId")
    val loanId: String
)