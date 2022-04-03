package com.appgame.prestador.model.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContactIdRequest(
    @SerializedName("contactId")
    @Expose
    val contactId: String
)