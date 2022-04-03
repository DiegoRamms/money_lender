package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.login.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("auth/logout")
    suspend fun logout(): LogoutResponse

}