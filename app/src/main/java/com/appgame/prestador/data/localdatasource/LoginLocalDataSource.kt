package com.appgame.prestador.data.localdatasource

interface LoginLocalDataSource {
    fun saveJWT(jwt: String)
    fun getJWT(): String
    fun deleteJWT()
    fun saveUserId(id: String)
    fun getUserId(): String
    fun deleteUserId()
}