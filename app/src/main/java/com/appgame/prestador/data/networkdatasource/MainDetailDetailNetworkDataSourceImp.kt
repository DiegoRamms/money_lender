package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.MainDetailService
import com.appgame.prestador.model.maindetail.MainDetailDTO
import com.appgame.prestador.model.maindetail.MainDetailResponse
import javax.inject.Inject

class MainDetailDetailNetworkDataSourceImp @Inject constructor(private val service: MainDetailService): MainDetailDetailNetworkDataSource {
    override suspend fun getMainDetail(): MainDetailResponse {
        return service.getMainDetail()
    }
}