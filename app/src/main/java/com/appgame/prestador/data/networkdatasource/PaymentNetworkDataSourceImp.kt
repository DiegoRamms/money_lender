package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.PaymentService
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.CreatePaymentRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetailResponse
import com.appgame.prestador.domain.payment.PaymentResponse

class PaymentNetworkDataSourceImp(private val paymentService: PaymentService): PaymentNetworkDataSource {
    override suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse {
        return paymentService.getLoanPaymentDetail(loanIdRequest)
    }

    override suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): PaymentResponse {
        return paymentService.createPayment(createPaymentRequest)
    }
}