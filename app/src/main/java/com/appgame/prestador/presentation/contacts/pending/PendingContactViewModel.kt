package com.appgame.prestador.presentation.contacts.pending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.use_case.contact.ContactUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PendingContactViewModel @Inject constructor(
    private val useCases: ContactUseCases,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
) : ViewModel() {

    private val _contactsToAccept = MutableLiveData<BaseResult<List<Contact>>>()
    val contactsToAccept get() = _contactsToAccept
    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading get() = _dataLoading
    private val _contactAccepted = MutableLiveData<BaseResult<Contact>>()
    val contactAccepted get() = _contactAccepted
    private val _contactDeleted = MutableLiveData<BaseResult<Contact>>()
    val contactDeleted get() = _contactDeleted

    init {
        getContactsToAccept()
    }

    fun setLoadingTrue() {
        _dataLoading.value = true
    }

    fun getContactsToAccept() {
        _contactsToAccept.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            BaseResult.resultBad()
            _dataLoading.value = false
        }) {
            withContext(ioDispatcher) { _contactsToAccept.postValue(useCases.getContactsToAccept()) }
            _dataLoading.value = false
        }
    }

    fun acceptContact(contactIdRequest: ContactIdRequest) {
        _contactAccepted.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contactAccepted.value = BaseResult.resultBad()
            _dataLoading.value = false
        }) {
            withContext(ioDispatcher) {
                _contactAccepted.postValue(useCases.acceptContact(contactIdRequest))
            }
            _dataLoading.value = false
            getContactsToAccept()
        }
    }

    fun deleteContact(contactIdRequest: ContactIdRequest) {
        _contactDeleted.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contactDeleted.value = BaseResult.resultBad()
            _dataLoading.value = false
        }) {
            withContext(ioDispatcher){
                _contactDeleted.postValue(useCases.deleteContact(contactIdRequest))
            }
            _dataLoading.value = false
            getContactsToAccept()
        }

    }

}