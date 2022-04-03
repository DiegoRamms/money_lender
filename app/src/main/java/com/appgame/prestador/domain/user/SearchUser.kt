package com.appgame.prestador.domain.user

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.data.repository.UserRepository
import com.appgame.prestador.model.user.SearchUserRequest
import com.appgame.prestador.model.user.SearchUserResponse
import com.appgame.prestador.model.user.User

class SearchUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(searchUserRequest: SearchUserRequest): BaseResult<User> {
        return userRepository.searchUser(searchUserRequest)
    }
}