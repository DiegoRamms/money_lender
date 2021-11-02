package com.appgame.prestador.domain.user

import com.google.gson.annotations.SerializedName

data class SearchUserRequest(
    @SerializedName("code")
    val code: String
) {
}