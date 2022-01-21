package com.appgame.prestador.domain.payment

import com.google.gson.annotations.SerializedName

data class PaymentDTO(
    @SerializedName("_id")
    var paymentId: Int,
    @SerializedName("loanId")
    var loanId: String,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("isAccepted")
    var isAccepted: Boolean
) {
}