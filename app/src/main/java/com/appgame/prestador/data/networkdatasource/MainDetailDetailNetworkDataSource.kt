package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.model.maindetail.MainDetailDTO
import com.appgame.prestador.model.maindetail.MainDetailResponse

interface MainDetailDetailNetworkDataSource {
    suspend fun getMainDetail(): MainDetailResponse
}