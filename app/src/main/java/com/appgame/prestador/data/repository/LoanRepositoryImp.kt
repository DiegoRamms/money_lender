package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.LoanNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.loan.*
import com.appgame.prestador.domain.repository.LoanRepository
import com.appgame.prestador.domain.user.UserIdRequest

class LoanRepositoryImp(private val loanNetworkDataSource: LoanNetworkDataSource): LoanRepository {
    override suspend fun getLoansByContactId(userIdRequest: UserIdRequest): BaseResult<LoansDetail> {
        val response = loanNetworkDataSource.getLoansByContactId(userIdRequest)
        return try {
            val loans = LoanDetailMapper.mapToDomainModel(response.loansDetailDTO)
            BaseResult.resultOK(response.msg,loans,response.code)
        }catch (e: Exception){
            BaseResult.resultBad()
        }
    }

    override suspend fun createLoan(createLoanRequest: CreateLoanRequest): BaseResult<Loan> {
        val response = loanNetworkDataSource.createLoan(createLoanRequest)

        return try {
            val loan = LoanMapper.mapToDomainModel(response.loanDTO)
            BaseResult.resultOK(response.msg,loan,response.code)
        }catch (e: Exception){
            BaseResult.resultBad()
        }

    }
}