package com.appgame.prestador.model.loan

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class LoansResponse constructor(
    @SerializedName("data")
    val loansDetailDTO: LoansDetailDTO
    ) : BaseResponse()