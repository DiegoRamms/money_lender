package com.appgame.prestador.use_case.user

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.repository.UserRepository
import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.SearchUserResponse
import com.appgame.prestador.domain.user.User

class SearchUser(private val userRepository: UserRepository) {

    suspend operator fun invoke(searchUserRequest: SearchUserRequest): BaseResult<User> {
        return userRepository.searchUser(searchUserRequest)
    }
}