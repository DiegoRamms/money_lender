package com.appgame.prestador.data.repository

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.CreateLoanRequest
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.model.loan.LoanChangeStatusRequest
import com.appgame.prestador.model.loan.LoansDetail
import com.appgame.prestador.model.user.UserIdRequest

interface LoanRepository {

    suspend fun getLoansByContactId(userIdRequest: UserIdRequest): BaseResult<LoansDetail>
    suspend fun createLoan(createLoanRequest: CreateLoanRequest): BaseResult<Loan>
    suspend fun changeStatusLoan(loanChangeStatusRequest: LoanChangeStatusRequest): BaseResult<Loan>

}