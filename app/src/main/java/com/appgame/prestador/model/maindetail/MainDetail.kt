package com.appgame.prestador.model.maindetail

import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.model.transaction.Transaction
import com.google.gson.annotations.SerializedName

data class MainDetail(
    val greeting: String,
    val loansAmount: String,
    val loansPercentagePaid: String,
    val debtsAmount: String,
    val debtsPercentagePaid: String,
    val loansCount: String,
    val debtsCount: String,
    val transactions: List<Transaction>,
    val loanNearDue: Loan
) {
}