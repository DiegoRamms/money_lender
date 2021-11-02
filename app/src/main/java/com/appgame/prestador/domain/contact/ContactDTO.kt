package com.appgame.prestador.domain.contact


import com.google.gson.annotations.SerializedName

data class ContactDTO(
    @SerializedName("code")
    val code: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("idContact")
    val idContact: String? = null
)