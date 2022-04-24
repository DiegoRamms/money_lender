package com.appgame.prestador.model.transaction

import com.google.gson.annotations.SerializedName
import java.util.*

data class TransactionDTO(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val date: Date,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("isAccepted")
    val isAccepted: Boolean
) {
}