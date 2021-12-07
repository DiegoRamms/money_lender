package com.appgame.prestador.domain.loan

import com.google.gson.annotations.SerializedName

data class LoansDetailDTO(
    @SerializedName("totalLoans")
    val totalLoans: Int,
    @SerializedName("totalDebts")
    val totalDebts: Int,
    @SerializedName("loans")
    val loans: List<LoanDTO>
) {
}