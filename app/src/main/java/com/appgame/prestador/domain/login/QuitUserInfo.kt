package com.appgame.prestador.domain.login

import com.appgame.prestador.data.repository.LoginRepository
import com.appgame.prestador.data.repository.MainDetailRepository

class QuitUserInfo(private val repository: LoginRepository) {
    suspend operator fun invoke() = repository.quitUserInfo()

}
