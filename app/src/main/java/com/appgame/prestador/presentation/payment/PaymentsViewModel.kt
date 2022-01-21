package com.appgame.prestador.presentation.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.LoanIdRequest
import com.appgame.prestador.domain.payment.CreatePaymentRequest
import com.appgame.prestador.domain.payment.LoanPaymentDetail
import com.appgame.prestador.use_case.payment.PaymentUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    private val useCases: PaymentUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : ViewModel() {

    private val _paymentsDetail = MutableLiveData<BaseResult<LoanPaymentDetail>>()
    val paymentDetail get() = _paymentsDetail
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

    fun addPayment(createPaymentRequest: CreatePaymentRequest){

    }

    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }


}