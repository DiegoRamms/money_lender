package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.loan.CreateLoanRequest
import com.appgame.prestador.model.loan.LoanChangeStatusRequest
import com.appgame.prestador.model.loan.LoanResponse
import com.appgame.prestador.model.loan.LoansResponse
import com.appgame.prestador.model.user.UserIdRequest

interface LoanNetworkDataSource {

    suspend fun getLoansByContactId(userIdRequest: UserIdRequest): LoansResponse
    suspend fun createLoan(createLoanRequest: CreateLoanRequest): LoanResponse
    suspend fun changeStatus(loanChangeStatusRequest: LoanChangeStatusRequest): LoanResponse

}