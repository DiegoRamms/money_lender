package com.appgame.prestador.utils.customexception

import android.util.Log
import com.appgame.prestador.model.BaseResult

class ShowUserException(message: String): Exception(message) {
}


fun <T> Throwable.showError(): BaseResult<T> {
     return if (this is ShowUserException){
        BaseResult.resultBad(this.message ?: "Error")
    }else BaseResult.resultBad()
}