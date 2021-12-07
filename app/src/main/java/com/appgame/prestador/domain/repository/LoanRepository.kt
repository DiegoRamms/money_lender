package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.loan.LoansDetail
import com.appgame.prestador.domain.user.UserIdRequest

interface LoanRepository {

    suspend fun getLoansByContactId(userIdRequest: UserIdRequest): BaseResult<LoansDetail>
    suspend fun createLoan(createLoanRequest: CreateLoanRequest): BaseResult<Loan>
}