package com.appgame.prestador.model.user

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("data")
    var user: UserDTO
) : BaseResponse()