package com.appgame.prestador.domain.login

import com.appgame.prestador.domain.user.User

data class LoginResponse(
    val status: Boolean,
    val msg: String,
    val jwt: String,
    val user: User
)