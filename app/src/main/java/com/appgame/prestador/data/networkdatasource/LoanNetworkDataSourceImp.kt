package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.LoanService
import com.appgame.prestador.model.loan.CreateLoanRequest
import com.appgame.prestador.model.loan.LoanResponse
import com.appgame.prestador.model.loan.LoansResponse
import com.appgame.prestador.model.user.UserIdRequest

class LoanNetworkDataSourceImp(private val service: LoanService): LoanNetworkDataSource {
    override suspend fun getLoansByContactId(userIdRequest: UserIdRequest): LoansResponse {
        return service.getLoansByContactId(userIdRequest)
    }

    override suspend fun createLoan(createLoanRequest: CreateLoanRequest): LoanResponse {
        return service.createLoan(createLoanRequest)
    }
}