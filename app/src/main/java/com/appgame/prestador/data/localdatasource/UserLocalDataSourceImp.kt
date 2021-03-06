package com.appgame.prestador.data.localdatasource

import com.appgame.prestador.data.localdatasource.preferences.LoginPreferences
import javax.inject.Inject

class UserLocalDataSourceImp @Inject constructor(private val loginPreferences: LoginPreferences): UserLocalDataSource {
    override fun saveJWT(jwt: String) {
        loginPreferences.saveJWT(jwt)
    }
    override fun getJWT(): String {
       return loginPreferences.getCurrentJWT()
    }

    override fun deleteJWT() {
        loginPreferences.deleteCurrentJWT()
    }

    override fun saveUserId(id: String) {
        loginPreferences.saveUserId(id)
    }

    override fun getUserId(): String {
        return loginPreferences.getUserId()
    }

    override fun deleteUserId() {
        loginPreferences.deleteUserId()
    }
}