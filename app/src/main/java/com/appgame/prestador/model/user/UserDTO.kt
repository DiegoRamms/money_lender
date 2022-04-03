package com.appgame.prestador.model.user

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("jwt")
    val jwt: String = ""
)