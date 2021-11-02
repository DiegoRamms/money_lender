package com.appgame.prestador.data.localdatasource.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class LoginPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun saveJWT(jwt: String) {
        sharedPreferences.edit {
            putString(LoginKeys.JWT.value, jwt)
            apply()
        }
    }

    fun getCurrentJWT(): String {
        return sharedPreferences.getString(LoginKeys.JWT.value, "")!!
    }

    fun deleteCurrentJWT() {
        sharedPreferences.edit {
            remove(LoginKeys.JWT.value)
            apply()
        }
    }
}

enum class LoginKeys(val value: String) {
    JWT("JWT")
}