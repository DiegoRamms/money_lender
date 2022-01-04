package com.appgame.prestador.use_case.payment

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetail
import com.appgame.prestador.domain.repository.PaymentRepository

class GetLoanPaymentDetail(private val repository: PaymentRepository) {
    suspend operator fun invoke(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail>{
        return repository.getLoanPaymentDetail(loanIdRequest)
    }
}