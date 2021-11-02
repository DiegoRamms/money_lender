package com.appgame.prestador.data.localdatasource

interface LoginLocalDataSource {
    fun saveJWT(jwt: String)
    fun getJWT(): String
    fun deleteJWT()
}