package com.appgame.prestador.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.model.login.LoginRequest
import com.appgame.prestador.model.login.LogoutResponse
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.login.LoginUseCases
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.user.User
import com.appgame.prestador.utils.customexception.ShowUserException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCases,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext
) : ViewModel(
) {

    private val _dialogLoadingState = MutableLiveData<Boolean>()
    private val _loginResponse = MutableLiveData<BaseResult<User>>()
    private val _logoutResponse = MutableLiveData<BaseResult<LogoutResponse>>()
    val loginResponse get() = _loginResponse
    val logoutResponse get() = _logoutResponse
    val dialogLoadingState get() = _dialogLoadingState


    fun login(loginRequest: LoginRequest) {
        _loginResponse.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, throwable ->
            _loginResponse.value =
                BaseResult.resultBad(if (throwable is ShowUserException) throwable.message.toString() else "Error")

            _dialogLoadingState.value = false
        }) {

            _loginResponse.postValue(loginUseCase.login(loginRequest))
            _dialogLoadingState.value = false
        }

    }

    fun logout() {
        _loginResponse.postValue(BaseResult.resultLoading())
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _loginResponse.postValue(BaseResult.resultBad("Error"))
            _dialogLoadingState.value = false
        }) {
            _logoutResponse.postValue(
                loginUseCase.quitUserInfo()
            )
            _dialogLoadingState.value = false
        }
    }

    fun setDialogLoadingTrue() {
        _dialogLoadingState.value = true
    }

}