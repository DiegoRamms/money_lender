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

    fun saveUserId(userId: String){
        sharedPreferences.edit {
            putString(LoginKeys.USER_ID.value,userId)
            apply()
        }
    }

    fun getUserId(): String {
        return sharedPreferences.getString(LoginKeys.USER_ID.value,"")!!
    }

    fun deleteUserId(){
        sharedPreferences.edit{
            remove(LoginKeys.USER_ID.value)
            apply()
        }
    }
}

enum class LoginKeys(val value: String) {
    JWT("JWT"),
    USER_ID("USER_ID")
}