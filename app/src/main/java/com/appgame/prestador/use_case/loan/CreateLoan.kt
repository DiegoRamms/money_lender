package com.appgame.prestador.use_case.loan

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.repository.LoanRepository

class CreateLoan(private val repository: LoanRepository) {
    suspend operator fun invoke(createLoanRequest: CreateLoanRequest): BaseResult<Loan>{
        return repository.createLoan(createLoanRequest)
    }
}