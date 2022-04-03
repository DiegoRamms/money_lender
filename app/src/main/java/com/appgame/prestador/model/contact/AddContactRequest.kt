package com.appgame.prestador.model.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AddContactRequest(
    @SerializedName("code")
    @Expose
    val code: String
) {
}