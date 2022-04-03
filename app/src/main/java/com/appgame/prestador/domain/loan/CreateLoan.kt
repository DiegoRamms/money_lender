package com.appgame.prestador.domain.loan

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.CreateLoanRequest
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.data.repository.LoanRepository

class CreateLoan(private val repository: LoanRepository) {
    suspend operator fun invoke(createLoanRequest: CreateLoanRequest): BaseResult<Loan>{
        return repository.createLoan(createLoanRequest)
    }
}