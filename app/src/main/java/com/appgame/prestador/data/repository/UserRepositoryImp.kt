package com.appgame.prestador.data.repository

import com.appgame.prestador.data.networkdatasource.UserNetworkDataSource
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.ContactDTOMapper
import com.appgame.prestador.domain.repository.UserRepository
import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.SearchUserResponse
import com.appgame.prestador.domain.user.User
import com.appgame.prestador.domain.user.UserDTOMapper

class UserRepositoryImp(private val userNetworkDataSource: UserNetworkDataSource): UserRepository {

    override suspend fun searchUser(searchUserRequest: SearchUserRequest): BaseResult<User> {
        val response = userNetworkDataSource.searchUser(searchUserRequest)
        return try {
            BaseResult.resultOK(response.msg,UserDTOMapper.mapToDomainModel(response.user),response.code)
        } catch (e: Exception){
            BaseResult.resultBad(response.msg)
        }
    }
}