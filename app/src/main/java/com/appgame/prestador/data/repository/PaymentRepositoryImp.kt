package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.PaymentNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.*
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

    override suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment> {

        val response = paymentNetworkDataSource.createPayment(createPaymentRequest)

        return try {
            val payment = PaymentDTOMapper.mapToDomainModel(response.paymentDTO)
            BaseResult.resultOK(response.msg,payment,response.code)
        }catch (e : Exception){
            BaseResult.resultBad(e.message.toString())
        }
    }
}