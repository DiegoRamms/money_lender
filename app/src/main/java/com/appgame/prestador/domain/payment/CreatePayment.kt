package com.appgame.prestador.domain.payment

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.model.payment.Payment
import com.appgame.prestador.data.repository.PaymentRepository

class CreatePayment(private val repository: PaymentRepository) {

    suspend  operator fun invoke(createPaymentRequest: CreatePaymentRequest): BaseResult<Payment>{
        return  repository.createPayment(createPaymentRequest = createPaymentRequest)
    }

}