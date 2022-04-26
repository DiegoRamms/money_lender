package com.appgame.prestador.data.repository

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.model.payment.LoanPaymentDetail
import com.appgame.prestador.model.payment.Payment
import com.appgame.prestador.model.payment.PaymentRequest

interface PaymentRepository {

    suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail>
    suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment>
    suspend fun acceptPayment(paymentRequest: PaymentRequest): BaseResult<Payment>
}