package com.appgame.prestador.presentation.contacts.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.LoansDetail
import com.appgame.prestador.model.user.UserIdRequest
import com.appgame.prestador.domain.loan.LoanUseCases
import com.appgame.prestador.domain.user.UserUseCases
import com.appgame.prestador.model.loan.Loan
import com.appgame.prestador.utils.customexception.showError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    private val loanUseCases: LoanUseCases,
    private val userUseCases: UserUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext,
): ViewModel() {

    private val _loans = MutableLiveData<BaseResult<LoansDetail>>()
    val loan get() = _loans
    private val _currentUserIdState = MutableLiveData<BaseResult<String>>()
    val currentUserIdState get() = _currentUserIdState
    private val _dialogLoading = MutableLiveData<Boolean>()
    val dialogLoading get() = _dialogLoading
    private val _loanUpdatedState = MutableLiveData<BaseResult<Loan>>()
    val loanUpdatedState get() = _loanUpdatedState



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

    fun getCurrentUserId(){
        _currentUserIdState.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _currentUserIdState.value = BaseResult.resultBad()
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher){
                _currentUserIdState.postValue(userUseCases.getCurrentUserId())
            }
            _dialogLoading.value = false
        }
    }

    fun acceptLoan(loanId: String){
        _loanUpdatedState.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, throwable ->
            _loanUpdatedState.value = throwable.showError()
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher){
                _loanUpdatedState.postValue(
                    loanUseCases.acceptLoan(loanId)
                )
            }
            _dialogLoading.value = false

        }
    }

    fun declineLoan(loanId: String){
        _loanUpdatedState.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, throwable ->
            _loanUpdatedState.value = throwable.showError()
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher){
                _loanUpdatedState.postValue(loanUseCases.deleteLoan(loanId))
            }
            _dialogLoading.value = false
        }
    }


    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }

}