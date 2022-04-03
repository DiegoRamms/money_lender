package com.appgame.prestador.domain.loan

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.LoansDetail
import com.appgame.prestador.data.repository.LoanRepository
import com.appgame.prestador.model.user.UserIdRequest

class GetLoansByContactId(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(userIdRequest: UserIdRequest): BaseResult<LoansDetail> {
        return loanRepository.getLoansByContactId(userIdRequest)
    }
}