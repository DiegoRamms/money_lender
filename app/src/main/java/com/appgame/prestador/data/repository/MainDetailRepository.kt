package com.appgame.prestador.data.repository

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.maindetail.MainDetail

interface MainDetailRepository {
    suspend fun getMainDetail(): BaseResult<MainDetail>
}