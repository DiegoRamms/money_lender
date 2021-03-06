package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.login.LogoutResponse
import com.appgame.prestador.data.networkdatasource.service.LoginService
import javax.inject.Inject

class LoginNetworkDataSourceImp @Inject constructor(private val service: LoginService): LoginNetworkDataSource {
    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
       return service.login(loginRequest)
    }

    override suspend fun logout(): LogoutResponse {
        return service.logout()
    }
}