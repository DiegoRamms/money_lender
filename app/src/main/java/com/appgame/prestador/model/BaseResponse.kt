package com.appgame.prestador.model

import com.appgame.prestador.utils.customexception.ShowUserException
import com.google.gson.annotations.SerializedName

abstract class BaseResponse(
    @SerializedName("msg")
    val msg: String = "",
    @SerializedName("status")
    val status: Boolean = false,
    @SerializedName("code")
    val code: Int = 2,
    @SerializedName("errors")
    val errors: ErrorsList? = null
)


data class ErrorsList(
    @SerializedName("errors")
    val listError: List<ErrorsService>
)


data class ErrorsService(
    @SerializedName("value")
    val value: String,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("param")
    val param: String,
    @SerializedName("location")
    val location: String

)


fun BaseResponse.handleServiceErrors() {
    if (!this.status) {
        this.errors?.let { errorsList -> throw ShowUserException(errorsList.listError[0].msg) }
            ?: throw ShowUserException(this.msg)
    }
}