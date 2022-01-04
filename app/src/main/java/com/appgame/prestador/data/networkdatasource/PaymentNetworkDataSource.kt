package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetailResponse

interface PaymentNetworkDataSource {

    suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): LoanPaymentDetailResponse

}