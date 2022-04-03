package com.appgame.prestador.model

class BaseResult <out T> (val message: String, val data: T? = null, val status: StatusResult, val code: Int? = null) {
    companion object {
        fun <T> resultOK(message: String = "OK",data: T?, code: Int? = null) = BaseResult(message,data, StatusResult.OK,code = code )
        fun resultBad(message: String = "Error") = BaseResult(message, null, StatusResult.BAD)
        fun resultLoading(message: String = "Cargando") = BaseResult(message, null, StatusResult.LOADING)
    }
}

enum class StatusResult {
    OK,
    BAD,
    LOADING
}


