package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.Loan

interface LoanRepository {

    suspend fun getLoansByContactId(idContactRequest: IdContactRequest): BaseResult<List<Loan>>
}