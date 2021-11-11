package com.appgame.prestador.domain.loan

import com.appgame.prestador.domain.Mapper

object LoanMapper: Mapper<LoanDTO, Loan> {
    override fun mapToDomainModel(model: LoanDTO): Loan {
        return Loan(model.idLoan,model.amount,model.dateLimit,model.dateStart,model.interestPercent,model.paymentsTime,model.userBorrower,model.userMoneyLender)
    }

    override fun mapFromDomainModel(domainModel: Loan): LoanDTO {
        return LoanDTO(domainModel.idLoan,domainModel.amount,domainModel.dateLimit,domainModel.dateStart,domainModel.interestPercent,domainModel.paymentsTime,domainModel.userBorrower,domainModel.userMoneyLender)
    }

    fun mapListToDomainModel(list: List<LoanDTO>): List<Loan>{
        return list.map { mapToDomainModel(it) }
    }

}