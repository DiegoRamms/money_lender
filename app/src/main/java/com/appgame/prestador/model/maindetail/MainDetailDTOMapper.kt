package com.appgame.prestador.model.maindetail

import com.appgame.prestador.model.Mapper
import com.appgame.prestador.model.transaction.TransactionDTOMapper

object MainDetailDTOMapper : Mapper<MainDetailDTO, MainDetail> {
    override fun mapToDomainModel(model: MainDetailDTO): MainDetail {
        return MainDetail(
            model.greeting,
            model.loansAmount,
            model.loansPercentagePaid,
            model.debtsAmount,
            model.debtsPercentagePaid,
            model.loansCount,
            model.debtsCount,
            TransactionDTOMapper.mapListToDomainModel(model.transactionsDTO)
        )
    }

    override fun mapFromDomainModel(domainModel: MainDetail): MainDetailDTO {
        return MainDetailDTO(
            domainModel.greeting,
            domainModel.loansAmount,
            domainModel.loansPercentagePaid,
            domainModel.debtsAmount,
            domainModel.debtsPercentagePaid,
            domainModel.loansCount,
            domainModel.debtsCount,
            TransactionDTOMapper.mapListFromDomainModel(domainModel.transactions)
        )
    }
}