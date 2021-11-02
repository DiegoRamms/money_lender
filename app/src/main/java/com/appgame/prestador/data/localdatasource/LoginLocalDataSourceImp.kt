package com.appgame.prestador.data.localdatasource

import com.appgame.prestador.data.localdatasource.preferences.LoginPreferences
import javax.inject.Inject

class LoginLocalDataSourceImp @Inject constructor(private val loginPreferences: LoginPreferences): LoginLocalDataSource {
    override fun saveJWT(jwt: String) {
        loginPreferences.saveJWT(jwt)
    }

    override fun getJWT(): String {
       return loginPreferences.getCurrentJWT()
    }

    override fun deleteJWT() {
        loginPreferences.deleteCurrentJWT()
    }
}