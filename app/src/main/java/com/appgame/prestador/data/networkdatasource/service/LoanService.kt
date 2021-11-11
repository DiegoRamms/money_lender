package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.LoansResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoanService {
    @POST("loan/getLoansByContactId")
    suspend fun getLoansByContactId(@Body idContactRequest: IdContactRequest): LoansResponse
}