package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.user.SearchUserRequest
import com.appgame.prestador.model.user.SearchUserResponse

interface UserNetworkDataSource {

    suspend fun searchUser(searchUserRequest: SearchUserRequest): SearchUserResponse

}