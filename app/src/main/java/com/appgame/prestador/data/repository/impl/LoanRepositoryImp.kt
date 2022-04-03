package com.appgame.prestador.data.repository.impl

import com.appgame.prestador.data.networkdatasource.LoanNetworkDataSource
import com.appgame.prestador.data.repository.LoanRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.*
import com.appgame.prestador.model.user.UserIdRequest
import com.appgame.prestador.utils.customexception.ShowUserException

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

    override suspend fun changeStatusLoan(loanChangeStatusRequest: LoanChangeStatusRequest): BaseResult<Loan> {
        val response = loanNetworkDataSource.changeStatus(loanChangeStatusRequest)
        if (!response.status) throw ShowUserException(response.msg)
        val loan = LoanMapper.mapToDomainModel(response.loanDTO)
        return BaseResult.resultOK(response.msg,loan)
    }
}