package com.appgame.prestador.domain.user

import com.google.gson.annotations.SerializedName

data class Type (
    @SerializedName("_id")
    val id: String,

    val name: String
)
