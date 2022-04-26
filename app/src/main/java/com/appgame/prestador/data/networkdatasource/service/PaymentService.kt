package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.model.payment.LoanPaymentDetailResponse
import com.appgame.prestador.model.payment.PaymentRequest
import com.appgame.prestador.model.payment.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentService {

    @POST("payment/loanPaymentDetail")
    suspend fun getLoanPaymentDetail(@Body loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse

    @POST("/payment/create")
    suspend fun createPayment(@Body createPaymentRequest: CreatePaymentRequest): PaymentResponse

    @POST("/payment/accept")
    suspend fun acceptPayment(@Body paymentRequest: PaymentRequest): PaymentResponse

}