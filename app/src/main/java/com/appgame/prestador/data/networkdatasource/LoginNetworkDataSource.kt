package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.login.LogoutResponse

interface LoginNetworkDataSource {

    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun logout(): LogoutResponse

}