package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.LoanNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.loan.LoanMapper
import com.appgame.prestador.domain.repository.LoanRepository

class LoanRepositoryImp(private val loanNetworkDataSource: LoanNetworkDataSource): LoanRepository {
    override suspend fun getLoansByContactId(idContactRequest: IdContactRequest): BaseResult<List<Loan>> {
        val response = loanNetworkDataSource.getLoansByContactId(idContactRequest)
        return try {
            val loans = LoanMapper.mapListToDomainModel(response.loans)
            BaseResult.resultOK(response.msg,loans,response.code)
        }catch (e: Exception){
            BaseResult.resultBad()
        }
    }
}