package com.appgame.prestador.use_case.loan

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.repository.LoanRepository

class GetLoansByContactId(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(idContactRequest: IdContactRequest): BaseResult<List<Loan>> {
        return loanRepository.getLoansByContactId(idContactRequest)
    }
}