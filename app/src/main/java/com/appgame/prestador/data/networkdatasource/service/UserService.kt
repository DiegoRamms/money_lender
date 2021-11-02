package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.SearchUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("user/find")
    suspend fun searchUser(@Body searchUserRequest: SearchUserRequest): SearchUserResponse

}