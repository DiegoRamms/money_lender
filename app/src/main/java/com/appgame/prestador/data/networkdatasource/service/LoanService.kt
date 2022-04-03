package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.model.loan.CreateLoanRequest
import com.appgame.prestador.model.loan.LoanChangeStatusRequest
import com.appgame.prestador.model.loan.LoanResponse
import com.appgame.prestador.model.loan.LoansResponse
import com.appgame.prestador.model.user.UserIdRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoanService {
    @POST("loan/getLoansByContactId")
    suspend fun getLoansByContactId(@Body userIdRequest: UserIdRequest): LoansResponse

    @POST("loan/create")
    suspend fun createLoan(@Body createLoanRequest: CreateLoanRequest): LoanResponse

    @POST("loan/changeStatus")
    suspend fun changeLoanStatus(loanChangeStatusRequest: LoanChangeStatusRequest): LoanResponse
}