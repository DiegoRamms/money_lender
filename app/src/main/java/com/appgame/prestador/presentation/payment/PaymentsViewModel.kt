package com.appgame.prestador.presentation.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.loan.LoanIdRequest
import com.appgame.prestador.model.payment.CreatePaymentRequest
import com.appgame.prestador.model.payment.LoanPaymentDetail
import com.appgame.prestador.model.payment.Payment
import com.appgame.prestador.domain.payment.PaymentUseCases
import com.appgame.prestador.domain.user.GetCurrentUserId
import com.appgame.prestador.domain.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val useCases: PaymentUseCases,
    private val userUseCases: UserUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : ViewModel() {

    private val _paymentsDetail = MutableLiveData<BaseResult<LoanPaymentDetail>>()
    val paymentDetail get() = _paymentsDetail
    private val _payment = MutableLiveData<BaseResult<Payment>>()
    val payment get() = _payment
    private val _currentUserIdState = MutableLiveData<BaseResult<String>>()
    val currentUserIdState get() = _currentUserIdState
    private val _dialogLoading = MutableLiveData<Boolean>()
    val dialogLoading get() = _dialogLoading


    fun getLoanPaymentDetail(loanIdRequest: LoanIdRequest) {
        _paymentsDetail.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, t ->
            _paymentsDetail.value = BaseResult.resultBad("${t.message}.")
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher) {
                _paymentsDetail.postValue(useCases.getLoanPaymentDetail(loanIdRequest))
                _dialogLoading.postValue(   false)
            }
        }
    }

    fun getCurrentUserId(){
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _currentUserIdState.value = BaseResult.resultBad()
        }) {
            withContext(ioDispatcher){
                _currentUserIdState.postValue(userUseCases.getCurrentUserId())
            }
        }
    }

    fun createPayment(createPaymentRequest: CreatePaymentRequest){
        _payment.value = BaseResult.resultLoading()

        viewModelScope.launch (mainDispatcher + CoroutineExceptionHandler { _, error ->
            _payment.postValue(BaseResult.resultBad(error.toString()))
            _dialogLoading.value = false
        }){
            withContext(ioDispatcher){
                _payment.postValue(useCases.createPayment(createPaymentRequest))
                _dialogLoading.postValue(false)
            }
        }

    }

    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }


}