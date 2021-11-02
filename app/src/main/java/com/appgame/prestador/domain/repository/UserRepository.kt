package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.SearchUserResponse
import com.appgame.prestador.domain.user.User

interface UserRepository {

    suspend fun searchUser(searchUserRequest: SearchUserRequest): BaseResult<User>

}