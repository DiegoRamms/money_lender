package com.appgame.prestador.model.user

import com.google.gson.annotations.SerializedName

data class SearchUserRequest(
    @SerializedName("code")
    val code: String
) {
}