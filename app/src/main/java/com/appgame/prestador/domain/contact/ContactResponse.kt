package com.appgame.prestador.domain.contact

import com.appgame.prestador.domain.BaseResponse
import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("data")
    val contact: ContactDTO? = null
): BaseResponse()