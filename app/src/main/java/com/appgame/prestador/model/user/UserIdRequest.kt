package com.appgame.prestador.model.user

import com.google.gson.annotations.SerializedName

data class UserIdRequest(
    @SerializedName("userId")
    val userId: String
) {
}