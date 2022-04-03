package com.appgame.prestador.data.repository

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.user.SearchUserRequest
import com.appgame.prestador.model.user.SearchUserResponse
import com.appgame.prestador.model.user.User

interface UserRepository {

    suspend fun searchUser(searchUserRequest: SearchUserRequest): BaseResult<User>
    suspend fun getCurrentUserId(): BaseResult<String>

}