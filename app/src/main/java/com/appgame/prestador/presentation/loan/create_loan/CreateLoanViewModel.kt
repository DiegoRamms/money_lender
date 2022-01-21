package com.appgame.prestador.presentation.loan.create_loan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.loan.CreateLoanRequest
import com.appgame.prestador.domain.loan.Loan
import com.appgame.prestador.domain.user.*
import com.appgame.prestador.use_case.loan.LoanUseCases
import com.appgame.prestador.utils.date.DATE_BASE_FORMAT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CreateLoanViewModel @Inject constructor(
    private val loanUseCases: LoanUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : ViewModel() {

    private val _loan = MutableLiveData<BaseResult<Loan>>()
    val loan get() = _loan
    private val _dialogLoading = MutableLiveData<Boolean>()
    val dialogLoading get() = _dialogLoading
    private val _formError = MutableLiveData<FormError>()
    val formError get() = _formError

    fun createLoan(createLoanRequest: CreateLoanRequest) {
        _loan.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, t ->
            _loan.value = BaseResult.resultBad(t.message + ". "+ t.localizedMessage + "  "+ t.stackTrace)
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher) {
                validForm(createLoanRequest)
            }
            _dialogLoading.value = false
        }
    }

    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }


    private suspend fun validForm(createLoanRequest: CreateLoanRequest) {
        createLoanRequest.amount.ifEmpty {
            _formError.postValue(AmountError("El monto no debe de ser vacio"))
            return
        }
        try {
            SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES"))
                .format(createLoanRequest.dateStart!!)
            SimpleDateFormat(DATE_BASE_FORMAT, Locale("es", "ES"))
                .format(createLoanRequest.dateLimit!!)
        } catch (e: Exception) {
            _formError.postValue(DateError("Eliga una fecha"))
            return
        }

        createLoanRequest.paymentsTime.ifEmpty {
            _formError.postValue(PaymentsTime(("Elije un plazo de pago")))
            return
        }

        createLoanRequest.interestTime.ifEmpty {
            _formError.postValue(InterestTime("Elije el tiempo de cobro del interes"))
            return
        }

       _loan.postValue(loanUseCases.createLoan(createLoanRequest))

    }

}