package com.appgame.prestador.domain.main

import com.appgame.prestador.data.repository.MainDetailRepository
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.maindetail.MainDetail
import javax.inject.Inject

class GetMainDetail @Inject constructor(private val repository: MainDetailRepository ) {
    suspend operator fun invoke(): BaseResult<MainDetail>{
        return repository.getMainDetail()
    }
}