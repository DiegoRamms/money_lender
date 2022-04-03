package com.appgame.prestador.model.contact

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class ContactResponse(
    @SerializedName("data")
    val contact: ContactDTO? = null
): BaseResponse()