package com.appgame.prestador.model.contact


import com.google.gson.annotations.SerializedName

data class ContactDTO(
    @SerializedName("userId")
    val userID: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("contactId")
    val contactId: String? = null
)