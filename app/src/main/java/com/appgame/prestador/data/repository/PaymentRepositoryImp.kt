package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.PaymentNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetail
import com.appgame.prestador.domain.payment.LoanPaymentDetailDTOMapper
import com.appgame.prestador.domain.repository.PaymentRepository

class PaymentRepositoryImp(private val paymentNetworkDataSource: PaymentNetworkDataSource): PaymentRepository {
    override suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail> {
        val response = paymentNetworkDataSource.getLoanPaymentDetail(loanIdRequest)
        return try {
            val loanPaymentDetail = LoanPaymentDetailDTOMapper.mapToDomainModel(response.loanPaymentDetailDTO)
            BaseResult.resultOK(response.msg,loanPaymentDetail,response.code)
        }catch (e: Exception){
            BaseResult.resultBad(e.message.toString())
        }
    }
}