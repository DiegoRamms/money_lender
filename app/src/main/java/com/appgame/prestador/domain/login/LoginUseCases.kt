package com.appgame.prestador.domain.login

import com.appgame.prestador.data.repository.LoginRepository

class LoginUseCases(
    val login: Login,
    val logout: Logout,
    val quitUserInfo: QuitUserInfo
) {

}