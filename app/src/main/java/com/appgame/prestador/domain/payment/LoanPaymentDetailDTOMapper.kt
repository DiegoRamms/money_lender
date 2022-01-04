package com.appgame.prestador.domain.payment

import android.icu.text.SimpleDateFormat
import com.appgame.prestador.domain.Mapper
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT

import java.util.*

object LoanPaymentDetailDTOMapper: Mapper<LoanPaymentDetailDTO,LoanPaymentDetail> {
    override fun mapToDomainModel(model: LoanPaymentDetailDTO): LoanPaymentDetail {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).format(model.nextPayTime).replace(".","")
        return LoanPaymentDetail(model.progressPayPercentage,model.progressPayText,model.nextPayMoney,date)
    }

    override fun mapFromDomainModel(domainModel: LoanPaymentDetail): LoanPaymentDetailDTO {
        val date = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).parse(domainModel.nextPayTime)
        return LoanPaymentDetailDTO(domainModel.progressPayPercentage,domainModel.progressPayText,domainModel.nextPayMoney,date)
    }


}