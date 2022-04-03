package com.appgame.prestador.model.loan

import com.appgame.prestador.model.Mapper

object LoanDetailMapper: Mapper<LoansDetailDTO,LoansDetail> {
    override fun mapToDomainModel(model: LoansDetailDTO): LoansDetail {
        return LoansDetail(model.totalLoans,model.totalDebts,LoanMapper.mapListToDomainModel(model.loans))
    }

    override fun mapFromDomainModel(domainModel: LoansDetail): LoansDetailDTO {
        return LoansDetailDTO(domainModel.totalLoans,domainModel.totalDebts,LoanMapper.mapListToModel(domainModel.loans))
    }
}