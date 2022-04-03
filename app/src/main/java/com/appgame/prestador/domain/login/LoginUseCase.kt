package com.appgame.prestador.domain.login

import com.appgame.prestador.data.repository.LoginRepository

class LoginUseCase(
    val login: Login,
    val logout: Logout
) {

}