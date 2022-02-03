package com.appgame.prestador.domain.payment

import com.appgame.prestador.domain.Mapper
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

object PaymentDTOMapper: Mapper<PaymentDTO,Payment> {
    override fun mapToDomainModel(model: PaymentDTO): Payment {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale.US).format(model.date)
        return Payment(model.paymentId,model.loanId,model.amount,date,model.isAccepted)
    }

    override fun mapFromDomainModel(domainModel: Payment): PaymentDTO {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale.US).parse(domainModel.date)
        return PaymentDTO(domainModel.paymentId,domainModel.loanId,domainModel.amount,date,domainModel.isAccepted)
    }
}