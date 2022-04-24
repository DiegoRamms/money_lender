package com.appgame.prestador.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.main.MainUseCases
import com.appgame.prestador.model.BaseResponse
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.maindetail.MainDetail
import com.appgame.prestador.utils.customexception.showError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCases: MainUseCases,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext
) : ViewModel() {

    private val _mainDetailState = MutableLiveData<BaseResult<MainDetail>>()
    val mainDetailState get() = _mainDetailState

    init {
        getMainDetail()
    }

    fun getMainDetail() {
        _mainDetailState.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, throwable ->
            _mainDetailState.value = throwable.showError()
        }) {
            withContext(ioDispatcher) {
                _mainDetailState.postValue(
                    mainUseCases.getMainDetail()
                )
            }
        }
    }

}