package com.appgame.prestador.domain.payment

import android.icu.text.SimpleDateFormat
import com.appgame.prestador.domain.Mapper
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT

import java.util.*

object LoanPaymentDetailDTOMapper: Mapper<LoanPaymentDetailDTO,LoanPaymentDetail> {
    override fun mapToDomainModel(model: LoanPaymentDetailDTO): LoanPaymentDetail {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).format(model.nextPayTime).replace(".","")
        return LoanPaymentDetail(model.totalToPay,model.progressPayPercentage,model.progressPayText,model.nextPayMoney,date,model.isPaidOut,PaymentDTOMapper.mapToListPayment(model.paymentsDTO),model.currentUserId)
    }

    override fun mapFromDomainModel(domainModel: LoanPaymentDetail): LoanPaymentDetailDTO {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).parse(domainModel.nextPayTime)
        return LoanPaymentDetailDTO(domainModel.totalToPay,domainModel.progressPayPercentage,domainModel.progressPayText,domainModel.nextPayMoney,date,domainModel.isPaidOut, PaymentDTOMapper.mapToListPaymentDto(domainModel.payments), domainModel.currentUserId)
    }

}