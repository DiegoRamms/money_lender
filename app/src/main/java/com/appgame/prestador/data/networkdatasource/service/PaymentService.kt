package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetailDTO
import com.appgame.prestador.domain.payment.LoanPaymentDetailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface PaymentService {

    @POST("payment/loanPaymentDetail")
    suspend fun getLoanPaymentDetail(@Body loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse

}