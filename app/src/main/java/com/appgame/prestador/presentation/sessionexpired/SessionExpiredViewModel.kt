package com.appgame.prestador.presentation.sessionexpired

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.login.LoginUseCases
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.login.LogoutResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SessionExpiredViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext
) : ViewModel() {



    fun quitUserInfo(){
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->  }) {
            withContext(ioDispatcher){
                loginUseCase.quitUserInfo()
            }
        }
    }

}