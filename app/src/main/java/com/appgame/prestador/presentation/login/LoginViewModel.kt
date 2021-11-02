package com.appgame.prestador.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.domain.login.LoginRequest
import com.appgame.prestador.domain.login.LoginResponse
import com.appgame.prestador.domain.login.LogoutResponse
import com.appgame.prestador.domain.repository.LoginRepository
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext
) : ViewModel(
) {

    private val _loginResponse = MutableLiveData<BaseResult<LoginResponse>>()
    private val _logoutResponse = MutableLiveData<BaseResult<LogoutResponse>>()
    val loginResponse get() = _loginResponse
    val logoutResponse get() = _logoutResponse


    fun login(loginRequest: LoginRequest) {
        _loginResponse.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _loginResponse.postValue(BaseResult.resultBad("Error"))
        }) {
            val loginResponse = withContext(ioDispatcher) {
                loginRepository.login(loginRequest)
            }
            _loginResponse.postValue(loginResponse)
        }

    }

    fun logout() {
        _loginResponse.postValue(BaseResult.resultLoading())
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _loginResponse.postValue(BaseResult.resultBad("Error"))
        }) {
            _logoutResponse.postValue(BaseResult.resultOK("Sesión cerrada",loginRepository.logout()))
        }

    }

}