package com.appgame.prestador.domain.payment

data class Payment(
    var paymentId: Int? = null,
    var loanId: String,
    var amount: String,
    var date: String,
    var isAccepted: Boolean
) {
}