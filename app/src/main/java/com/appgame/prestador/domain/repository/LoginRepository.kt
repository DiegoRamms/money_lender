package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.login.LoginRequest
import com.appgame.prestador.domain.login.LoginResponse
import com.appgame.prestador.domain.login.LogoutResponse
import com.appgame.prestador.domain.BaseResult

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): BaseResult<LoginResponse>
    suspend fun logout(): LogoutResponse
}