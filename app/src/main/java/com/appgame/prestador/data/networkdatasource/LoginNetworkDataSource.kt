package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.login.LoginRequest
import com.appgame.prestador.domain.login.LoginResponse
import com.appgame.prestador.domain.login.LogoutResponse

interface LoginNetworkDataSource {

    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun logout(): LogoutResponse

}