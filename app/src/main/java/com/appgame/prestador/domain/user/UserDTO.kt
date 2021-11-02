package com.appgame.prestador.domain.user

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("uid")
    val uid: String
) {
}