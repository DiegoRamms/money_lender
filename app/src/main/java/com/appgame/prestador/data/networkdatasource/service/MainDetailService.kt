package com.appgame.prestador.data.networkdatasource.service

import com.appgame.prestador.model.maindetail.MainDetailDTO
import com.appgame.prestador.model.maindetail.MainDetailResponse
import retrofit2.http.POST

interface MainDetailService {
    @POST("main/getMainDetail")
    suspend fun getMainDetail(): MainDetailResponse
}