package com.appgame.prestador.domain.contact


import com.appgame.prestador.domain.BaseResponse
import com.appgame.prestador.domain.contact.ContactDTO
import com.google.gson.annotations.SerializedName

class ContactsResponse(
    @SerializedName("data")
    val contacts: List<ContactDTO>
): BaseResponse() {
}