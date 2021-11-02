package com.appgame.prestador.domain.user

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("data")
    val user: UserDTO
) : BaseResponse()