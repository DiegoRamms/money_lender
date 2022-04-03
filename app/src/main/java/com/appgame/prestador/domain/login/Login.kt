package com.appgame.prestador.domain.login

import com.appgame.prestador.data.repository.LoginRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.user.User

class Login(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): BaseResult<User>{
        return loginRepository.login(loginRequest)
    }
}