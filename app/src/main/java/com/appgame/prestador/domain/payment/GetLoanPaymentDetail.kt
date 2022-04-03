package com.appgame.prestador.domain.payment

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.LoanPaymentDetail
import com.appgame.prestador.data.repository.PaymentRepository

class GetLoanPaymentDetail(private val repository: PaymentRepository) {
    suspend operator fun invoke(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail>{
        return repository.getLoanPaymentDetail(loanIdRequest)
    }
}