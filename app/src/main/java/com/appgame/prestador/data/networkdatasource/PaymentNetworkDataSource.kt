package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.model.payment.LoanPaymentDetailResponse
import com.appgame.prestador.model.payment.PaymentRequest
import com.appgame.prestador.model.payment.PaymentResponse

interface PaymentNetworkDataSource {

    suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse
    suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): PaymentResponse
    suspend fun acceptPayment(paymentRequest: PaymentRequest): PaymentResponse

}