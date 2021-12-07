package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.LoanService
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.LoanResponse
import com.appgame.prestador.domain.loan.LoansResponse
import com.appgame.prestador.domain.user.UserIdRequest

class LoanNetworkDataSourceImp(private val service: LoanService): LoanNetworkDataSource {
    override suspend fun getLoansByContactId(userIdRequest: UserIdRequest): LoansResponse {
        return service.getLoansByContactId(userIdRequest)
    }

    override suspend fun createLoan(createLoanRequest: CreateLoanRequest): LoanResponse {
        return service.createLoan(createLoanRequest)
    }
}