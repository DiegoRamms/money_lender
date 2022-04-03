package com.appgame.prestador.domain.user

import com.appgame.prestador.data.repository.UserRepository
import com.appgame.prestador.model.BaseResult

class GetCurrentUserId(private val userRepository: UserRepository) {

    suspend operator fun invoke(): BaseResult<String> {
        return userRepository.getCurrentUserId()
    }


}