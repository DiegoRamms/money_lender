package com.appgame.prestador.use_case.loan

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.loan.LoansDetail
import com.appgame.prestador.domain.repository.LoanRepository
import com.appgame.prestador.domain.user.UserIdRequest

class GetLoansByContactId(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(userIdRequest: UserIdRequest): BaseResult<LoansDetail> {
        return loanRepository.getLoansByContactId(userIdRequest)
    }
}