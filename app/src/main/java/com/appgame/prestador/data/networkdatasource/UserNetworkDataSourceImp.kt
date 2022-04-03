package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.UserService
import com.appgame.prestador.model.user.SearchUserRequest
import com.appgame.prestador.model.user.SearchUserResponse

class UserNetworkDataSourceImp(private val service: UserService): UserNetworkDataSource {

    override suspend fun searchUser(searchUserRequest: SearchUserRequest): SearchUserResponse {
        return service.searchUser(searchUserRequest)
    }
}