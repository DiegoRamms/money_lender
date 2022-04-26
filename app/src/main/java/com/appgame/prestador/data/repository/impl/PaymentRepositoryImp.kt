package com.appgame.prestador.data.repository.impl

import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.data.networkdatasource.PaymentNetworkDataSource
import com.appgame.prestador.data.repository.PaymentRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.handleServiceErrors
import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.*

class PaymentRepositoryImp(
    private val paymentNetworkDataSource: PaymentNetworkDataSource) :
    PaymentRepository {
    override suspend fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest): BaseResult<LoanPaymentDetail> {
        val response = paymentNetworkDataSource.getLoanPaymentDetail(loanIdRequest)
        return try {
            val loanPaymentDetail =
                LoanPaymentDetailDTOMapper.mapToDomainModel(response.loanPaymentDetailDTO)
            BaseResult.resultOK(response.msg, loanPaymentDetail, response.code)
        } catch (e: Exception) {
            BaseResult.resultBad(e.message.toString())
        }
    }

    override suspend fun createPayment(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment> {

        val response = paymentNetworkDataSource.createPayment(createPaymentRequest)
        response.handleServiceErrors()
        val payment = PaymentDTOMapper.mapToDomainModel(response.paymentDTO)
        return BaseResult.resultOK(response.msg, payment, response.code)
    }

    override suspend fun acceptPayment(paymentRequest: PaymentRequest): BaseResult<Payment> {
        val response = paymentNetworkDataSource.acceptPayment(paymentRequest)
        response.handleServiceErrors()
        val payment = PaymentDTOMapper.mapToDomainModel(response.paymentDTO)
        return BaseResult.resultOK(response.msg,payment, response.code)
    }
}