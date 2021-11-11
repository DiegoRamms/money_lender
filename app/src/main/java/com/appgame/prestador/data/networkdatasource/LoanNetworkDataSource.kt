package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.LoansResponse

interface LoanNetworkDataSource {

    suspend fun getLoansByContactId(idContactRequest: IdContactRequest): LoansResponse


}