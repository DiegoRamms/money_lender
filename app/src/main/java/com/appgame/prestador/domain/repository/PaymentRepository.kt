package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.CreatePaymentRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetail
import com.appgame.prestador.domain.payment.Payment

interface PaymentRepository {

    suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail>
    suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment>

}