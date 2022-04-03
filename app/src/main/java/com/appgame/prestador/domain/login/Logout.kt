package com.appgame.prestador.domain.login

import com.appgame.prestador.data.repository.LoginRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.login.LogoutResponse

class Logout(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(): BaseResult<LogoutResponse> {
        return loginRepository.logout()
    }
}