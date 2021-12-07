package com.appgame.prestador.domain.loan

import android.icu.text.SimpleDateFormat
import com.appgame.prestador.domain.Mapper
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import java.util.*

object LoanMapper: Mapper<LoanDTO, Loan> {
    override fun mapToDomainModel(model: LoanDTO): Loan {
        val dateStart = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).format(model.dateStart).replace(".","")
        val dateLimit = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).format(model.dateLimit).replace(".","")
        return Loan(model.loanId,model.amount,dateLimit,dateStart,model.interestPercent,model.paymentsTime,model.interestTime,model.userBorrower,model.userMoneyLender,model.status,model.type,model.comment)
    }

    override fun mapFromDomainModel(domainModel: Loan): LoanDTO {
        val dateStart = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).parse(domainModel.dateStart)
        val dateLimit = SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).parse(domainModel.dateLimit)
        return LoanDTO(domainModel.loanId,domainModel.amount,dateLimit,dateStart,domainModel.interestPercent,domainModel.paymentsTime,domainModel.interestTime,domainModel.userBorrower,domainModel.userMoneyLender, domainModel.status, domainModel.type,domainModel.comment)
    }

    fun mapListToDomainModel(list: List<LoanDTO>): List<Loan>{
        return list.map { mapToDomainModel(it) }
    }

    fun mapListToModel(list: List<Loan>): List<LoanDTO>{
        return list.map { mapFromDomainModel(it) }
    }
}