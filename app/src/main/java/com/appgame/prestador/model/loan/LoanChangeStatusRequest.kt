package com.appgame.prestador.model.loan

import com.google.gson.annotations.SerializedName

data class LoanChangeStatusRequest(
    @SerializedName("loanId")
    val loanId: String,
    @SerializedName("status")
    val status: String
) {
}