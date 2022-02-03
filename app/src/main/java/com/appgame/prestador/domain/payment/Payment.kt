package com.appgame.prestador.domain.payment

data class Payment(
    var paymentId: String,
    var loanId: String,
    var amount: String,
    var date: String,
    var isAccepted: Boolean
) {
}