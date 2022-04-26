package com.appgame.prestador.data.repository

import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.login.LogoutResponse
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.user.User

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): BaseResult<User>
    suspend fun logout(): BaseResult<LogoutResponse>
    suspend fun quitUserInfo(): BaseResult<LogoutResponse>
}