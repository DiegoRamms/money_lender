package com.appgame.prestador.domain.user

import com.google.gson.annotations.SerializedName

data class UserIdRequest(
    @SerializedName("userId")
    val userId: String
) {
}