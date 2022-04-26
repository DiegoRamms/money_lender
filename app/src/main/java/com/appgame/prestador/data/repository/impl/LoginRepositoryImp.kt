package com.appgame.prestador.data.repository.impl

import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LoginResponse
import com.appgame.prestador.model.login.LogoutResponse
import com.appgame.prestador.data.networkdatasource.LoginNetworkDataSource
import com.appgame.prestador.data.repository.LoginRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.Mapper
import com.appgame.prestador.model.user.User
import com.appgame.prestador.model.user.UserDTOMapper
import com.appgame.prestador.utils.CODE_OK_RESPONSE
import com.appgame.prestador.utils.customexception.ShowUserException

import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val loginLocalDataSource: UserLocalDataSource,
) : LoginRepository {


    override suspend fun login(loginRequest: LoginRequest): BaseResult<User> {
        val response = loginNetworkDataSource.login(loginRequest)
        if (response.status) {
            loginLocalDataSource.saveJWT(response.user.jwt)
            loginLocalDataSource.saveUserId(response.user.uid)
        } else throw ShowUserException(response.msg)
        return BaseResult.resultOK(message = response.msg, data = UserDTOMapper.mapToDomainModel(response.user), code= response.code)
    }

    override suspend fun logout(): BaseResult<LogoutResponse> {

        /*withContext(coroutineContext) {
            val timeInit = System.currentTimeMillis()
            val logoutNetwork = async {

                    loginNetworkDataSource.logout()

            }

            val logoutLocal = async {

                    //loginLocalDataSource.deleteJWT()

            }

            logoutLocal.await()
            logoutNetwork.await()
            val timeFinal = System.currentTimeMillis() - timeInit
            Log.e("Time", timeFinal.toString())

        }*/
        loginNetworkDataSource.logout()
        loginLocalDataSource.deleteUserId()
        loginLocalDataSource.deleteJWT()

        return BaseResult.resultOK(message = "Sesión cerrada", data = LogoutResponse(true, "Sesión cerrada"), CODE_OK_RESPONSE)

    }

    override suspend fun quitUserInfo(): BaseResult<LogoutResponse> {
        loginLocalDataSource.deleteJWT()
        loginLocalDataSource.deleteUserId()
        return BaseResult.resultOK(message = "Sesión cerrada", data = LogoutResponse(true, "Sesión cerrada"), CODE_OK_RESPONSE)
    }
}