package com.appgame.prestador.model

import com.google.gson.annotations.SerializedName

abstract class BaseResponse(
    @SerializedName("msg")
    val msg: String = "",
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("code")
    val code: Int = 2
)