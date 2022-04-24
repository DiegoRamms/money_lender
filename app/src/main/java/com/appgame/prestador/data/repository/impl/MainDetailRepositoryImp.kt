package com.appgame.prestador.data.repository.impl

import com.appgame.prestador.data.networkdatasource.MainDetailDetailNetworkDataSource
import com.appgame.prestador.data.repository.MainDetailRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.handleServiceErrors
import com.appgame.prestador.model.maindetail.MainDetail
import com.appgame.prestador.model.maindetail.MainDetailDTOMapper
import com.appgame.prestador.utils.customexception.ShowUserException
import javax.inject.Inject

class MainDetailRepositoryImp @Inject constructor(
    private val networkDataSource: MainDetailDetailNetworkDataSource
) : MainDetailRepository {

    override suspend fun getMainDetail(): BaseResult<MainDetail> {
        val response = networkDataSource.getMainDetail()
        response.handleServiceErrors()
        val mainDetail = MainDetailDTOMapper.mapToDomainModel(response.mainDetailDTO)
        return BaseResult.resultOK(response.msg, mainDetail, response.code)
    }

}