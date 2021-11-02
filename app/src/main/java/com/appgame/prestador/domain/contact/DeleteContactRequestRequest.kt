package com.appgame.prestador.domain.contact

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DeleteContactRequestRequest(
    @SerializedName("id")
    @Expose
    val idContact: String
)