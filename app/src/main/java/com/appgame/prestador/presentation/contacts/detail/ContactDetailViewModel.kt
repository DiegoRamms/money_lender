package com.appgame.prestador.presentation.contacts.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.loan.LoansDetail
import com.appgame.prestador.domain.user.UserIdRequest
import com.appgame.prestador.use_case.loan.LoanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    private val loanUseCases: LoanUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext,
): ViewModel() {

    private val _loans = MutableLiveData<BaseResult<LoansDetail>>()
    val loan get() = _loans
    private val _dialogLoading = MutableLiveData<Boolean>()
    val dialogLoading get() = _dialogLoading



    fun getLoansByContactId(userIdRequest: UserIdRequest){
        _loans.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _loans.value = BaseResult.resultBad()
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher){
                _loans.postValue(loanUseCases.getLoansByContactId(userIdRequest))
            }
            _dialogLoading.value = false
        }
    }

    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }

}