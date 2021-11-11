package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.LoanService
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.LoansResponse

class LoanNetworkDataSourceImp(private val service: LoanService): LoanNetworkDataSource {
    override suspend fun getLoansByContactId(idContactRequest: IdContactRequest): LoansResponse {
        return service.getLoansByContactId(idContactRequest)
    }
}