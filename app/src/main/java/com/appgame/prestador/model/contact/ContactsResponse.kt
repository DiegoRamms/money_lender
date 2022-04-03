package com.appgame.prestador.model.contact


import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

class ContactsResponse(
    @SerializedName("data")
    val contacts: List<ContactDTO>
): BaseResponse() {
}