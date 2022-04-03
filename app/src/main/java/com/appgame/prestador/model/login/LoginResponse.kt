package com.appgame.prestador.model.login

import com.appgame.prestador.model.BaseResponse
import com.appgame.prestador.model.user.User
import com.appgame.prestador.model.user.UserDTO
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val user: UserDTO
): BaseResponse()
