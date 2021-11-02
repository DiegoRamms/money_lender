package com.appgame.prestador.presentation.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appgame.prestador.data.localdatasource.LoginLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MenuViewModel @Inject constructor(private val localDataSource: LoginLocalDataSource) : ViewModel() {

    private val _isLogout = MutableLiveData<Boolean>()
    val isLogout get() = _isLogout

    init {
        checkJWT()
    }

    private fun checkJWT(){
        _isLogout.value = localDataSource.getJWT() == ""
    }

    fun logOut(){
        _isLogout.value = true
    }

}