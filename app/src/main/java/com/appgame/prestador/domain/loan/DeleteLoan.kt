package com.appgame.prestador.domain.loan

import com.appgame.prestador.data.repository.LoanRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.model.loan.LoanChangeStatusRequest

class DeleteLoan(private val loanRepository: LoanRepository) {
    suspend operator fun invoke(loanId: String): BaseResult<Loan>{
        return loanRepository.changeStatusLoan(LoanChangeStatusRequest(loanId,"REJECTED"))
    }
}