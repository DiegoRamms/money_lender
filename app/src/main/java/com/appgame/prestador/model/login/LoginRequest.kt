package com.appgame.prestador.model.login

data class LoginRequest (
    var email: String,
    var password: String,
    var appType: String
) {
}