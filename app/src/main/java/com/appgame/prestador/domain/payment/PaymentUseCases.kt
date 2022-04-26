package com.appgame.prestador.domain.payment

class PaymentUseCases(
    val getLoanPaymentDetail: GetLoanPaymentDetail,
    val createPayment: CreatePayment,
    val acceptPayment: AcceptPayment
)