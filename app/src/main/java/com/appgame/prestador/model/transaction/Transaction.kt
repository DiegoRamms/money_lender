package com.appgame.prestador.model.transaction

data class Transaction(
    val id: String,
    val name: String,
    val date: String,
    val amount: String,
    val type: TransactionType,
    val isAccepted: Boolean
) {
}


enum class TransactionType(val value: String){
    PAYMENT("PAYMENT"),
    DEBT("DEBT")
}