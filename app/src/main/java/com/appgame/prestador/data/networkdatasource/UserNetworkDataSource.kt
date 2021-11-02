package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.SearchUserResponse

interface UserNetworkDataSource {

    suspend fun searchUser(searchUserRequest: SearchUserRequest): SearchUserResponse

}