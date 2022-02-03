package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.CreatePaymentRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetailResponse
import com.appgame.prestador.domain.payment.PaymentResponse

interface PaymentNetworkDataSource {

    suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse
    suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): PaymentResponse

}