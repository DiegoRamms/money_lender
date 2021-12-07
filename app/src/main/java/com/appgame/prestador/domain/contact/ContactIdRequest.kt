package com.appgame.prestador.domain.contact

import com.appgame.prestador.use_case.loan.GetLoansByContactId
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ContactIdRequest(
    @SerializedName("contactId")
    @Expose
    val contactId: String
)