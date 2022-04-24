package com.appgame.prestador.model.transaction

import android.icu.text.SimpleDateFormat
import com.appgame.prestador.model.Mapper
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT

import java.util.*

object TransactionDTOMapper : Mapper<TransactionDTO, Transaction> {
    override fun mapToDomainModel(model: TransactionDTO): Transaction {
        return Transaction(
            model.id,
            model.name,
            SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES")).format(model.date),
            model.amount,
            if (model.type == TransactionType.PAYMENT.value) TransactionType.PAYMENT else TransactionType.DEBT,
            model.isAccepted
        )
    }

    override fun mapFromDomainModel(domainModel: Transaction): TransactionDTO {
        return TransactionDTO(
            domainModel.id,
            domainModel.name,
            SimpleDateFormat(DATE_BASE_FORMAT, Locale("es","ES")).parse(domainModel.date),
            domainModel.amount,
            domainModel.type.value,
            domainModel.isAccepted
        )
    }

    fun mapListToDomainModel(list: List<TransactionDTO>): List<Transaction>{
        return list.map { mapToDomainModel(it) }
    }

    fun mapListFromDomainModel(list: List<Transaction>): List<TransactionDTO>{
        return list.map { mapFromDomainModel(it) }
    }
}