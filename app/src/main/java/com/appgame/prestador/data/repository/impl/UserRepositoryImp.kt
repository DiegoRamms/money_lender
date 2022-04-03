package com.appgame.prestador.data.repository.impl

import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.data.networkdatasource.UserNetworkDataSource
import com.appgame.prestador.data.repository.UserRepository
import com.appgame.prestador.domain.user.UserUseCases
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.user.SearchUserRequest
import com.appgame.prestador.model.user.SearchUserResponse
import com.appgame.prestador.model.user.User
import com.appgame.prestador.model.user.UserDTOMapper
import com.appgame.prestador.utils.CODE_OK_RESPONSE

class UserRepositoryImp(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    /*TODO Cambiar el Base Result en el UseCase
      */
    @Throws
    override suspend fun searchUser(searchUserRequest: SearchUserRequest): BaseResult<User> {
        val response = userNetworkDataSource.searchUser(searchUserRequest)
        if (response.status) return  BaseResult.resultOK(response.msg,UserDTOMapper.mapToDomainModel(response.user),response.code)
        else throw Exception(response.msg)
    }

    override suspend fun getCurrentUserId(): BaseResult<String> {
        return BaseResult.resultOK(message = "Usuario Actual", data = userLocalDataSource.getUserId())
    }
}