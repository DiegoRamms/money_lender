package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.LoanResponse
import com.appgame.prestador.domain.loan.LoansResponse
import com.appgame.prestador.domain.user.UserIdRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface LoanService {
    @POST("loan/getLoansByContactId")
    suspend fun getLoansByContactId(@Body userIdRequest: UserIdRequest): LoansResponse

    @POST("loan/create")
    suspend fun createLoan(@Body createLoanRequest: CreateLoanRequest): LoanResponse
}