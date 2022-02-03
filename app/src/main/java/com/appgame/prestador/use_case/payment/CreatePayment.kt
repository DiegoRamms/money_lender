package com.appgame.prestador.use_case.payment

import com.appgame.prestador.data.repository.PaymentRepositoryImp
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.payment.CreatePaymentRequest
import com.appgame.prestador.domain.payment.Payment
import com.appgame.prestador.domain.repository.PaymentRepository

class CreatePayment(private val repository: PaymentRepository) {

    suspend  operator fun invoke(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment>{
        return  repository.createPayment(createPaymentRequest = createPaymentRequest)
    }

}