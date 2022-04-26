package com.appgame.prestador.model.maindetail

import com.appgame.prestador.model.BaseResponse
import com.appgame.prestador.model.loan.LoanDTO
import com.appgame.prestador.model.transaction.TransactionDTO
import com.google.gson.annotations.SerializedName

data class MainDetailDTO(
    @SerializedName("greeting")
    val greeting: String,
    @SerializedName("loansAmount")
    val loansAmount: String,
    @SerializedName("loansPercentagePaid")
    val loansPercentagePaid: String,
    @SerializedName("debtsAmount")
    val debtsAmount: String,
    @SerializedName("debtsPercentagePaid")
    val debtsPercentagePaid: String,
    @SerializedName("loansCount")
    val loansCount: String,
    @SerializedName("debtsCount")
    val debtsCount: String,
    @SerializedName("transactions")
    val transactionsDTO: List<TransactionDTO>,
    @SerializedName("loanNearDue")
    val loanNearDueDTO: LoanDTO
)