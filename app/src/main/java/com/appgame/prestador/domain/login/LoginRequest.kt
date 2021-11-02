package com.appgame.prestador.domain.login

data class LoginRequest (
    var email: String,
    var password: String,
    var appType: String
) {
}