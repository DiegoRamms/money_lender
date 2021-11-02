package com.appgame.prestador.data.repository

import android.util.Log
import com.appgame.prestador.data.localdatasource.LoginLocalDataSource
import com.appgame.prestador.domain.login.LoginRequest
import com.appgame.prestador.domain.login.LoginResponse
import com.appgame.prestador.domain.login.LogoutResponse
import com.appgame.prestador.data.networkdatasource.LoginNetworkDataSource
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.domain.repository.LoginRepository
import com.appgame.prestador.domain.BaseResult
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class LoginRepositoryImp @Inject constructor(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val loginLocalDataSource: LoginLocalDataSource,
) : LoginRepository {


    override suspend fun login(loginRequest: LoginRequest): BaseResult<LoginResponse> {
        return try {
            val response = loginNetworkDataSource.login(loginRequest)
            if (response.status) {
                loginLocalDataSource.saveJWT(response.jwt)
                BaseResult.resultOK(response.msg, response)
            } else BaseResult.resultBad(response.msg)

        } catch (e: Exception) {
            BaseResult.resultBad()
        }
    }

    override suspend fun logout(): LogoutResponse {

        withContext(coroutineContext) {
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

        }

        return LogoutResponse(true, "Sesión cerrada")


    }


}