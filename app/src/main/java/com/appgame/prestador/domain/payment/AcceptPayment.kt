package com.appgame.prestador.domain.payment

import com.appgame.prestador.data.repository.PaymentRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.payment.Payment
import com.appgame.prestador.model.payment.PaymentRequest

class AcceptPayment(private val paymentRepository: PaymentRepository) {

    suspend operator fun invoke(paymentRequest: PaymentRequest): BaseResult<Payment>{
        return paymentRepository.acceptPayment(paymentRequest)
    }

}
