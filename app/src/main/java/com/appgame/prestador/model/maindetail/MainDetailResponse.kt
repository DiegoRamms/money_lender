package com.appgame.prestador.model.maindetail

import com.appgame.prestador.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class MainDetailResponse(
    @SerializedName("data")
    val mainDetailDTO: MainDetailDTO
):BaseResponse() {
}