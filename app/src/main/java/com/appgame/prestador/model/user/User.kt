package com.appgame.prestador.model.user

import com.appgame.prestador.model.BaseResponse

data class User (
    val name: String,
    val email: String,
    val code: String,
    val createdAt: String,
    val uid: String
)
