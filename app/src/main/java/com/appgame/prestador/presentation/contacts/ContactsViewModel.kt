package com.appgame.prestador.presentation.contacts


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.use_case.contact.ContactUseCases
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.IdContactRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactsUseCases: ContactUseCases,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    @IODispatcher private val ioDispatcher: CoroutineContext
) : ViewModel() {


    private val _contacts = MutableLiveData<BaseResult<List<Contact>>>()
    val contacts get() = _contacts
    private val _dialogLoading = MutableLiveData<Boolean>()
    val dialogLoading get() = _dialogLoading
    private val _contactDeleted = MutableLiveData<BaseResult<Contact>>()
    val contactDeleted get() = _contactDeleted

    init {
        getContacts()
    }

    fun getContacts() {
        _contacts.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contacts.value = BaseResult.resultBad("Error")
            _dialogLoading.value = false
        }) {
            withContext(ioDispatcher) {
                _contacts.postValue(contactsUseCases.getContacts())
            }
            _dialogLoading.value = false
        }
    }

    fun deleteContact(idContactRequest: IdContactRequest) {
        _contactDeleted.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contactDeleted.value = BaseResult.resultBad()
            _dialogLoading.value = false
        }) {

           withContext(mainDispatcher){
              _contactDeleted.value= contactsUseCases.deleteContact(idContactRequest)
           }
            _dialogLoading.value = false

        }
    }

    fun setDialogLoadingTrue() {
        _dialogLoading.value = true
    }


}