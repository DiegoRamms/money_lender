package com.appgame.prestador.domain.payment

import com.google.gson.annotations.SerializedName
import java.util.*

data class PaymentDTO(
    @SerializedName("_id")
    var paymentId: String,
    @SerializedName("user")
    var user: String,
    @SerializedName("loanId")
    var loanId: String,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("date")
    var date: Date,
    @SerializedName("isAccepted")
    var isAccepted: Boolean
) {
}