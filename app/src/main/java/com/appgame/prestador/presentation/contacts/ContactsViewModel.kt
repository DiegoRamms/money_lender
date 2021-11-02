package com.appgame.prestador.presentation.contacts


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.domain.contact.ContactDTO
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.use_case.contact.ContactUseCases
import com.appgame.prestador.domain.BaseResult
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


    private val _contacts = MutableLiveData<BaseResult<List<ContactDTO>>>()
    val contacts get() = _contacts

    init {
        getContacts()
    }

    private fun getContacts() {
        _contacts.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contacts.postValue(BaseResult.resultBad("Error"))
        }) {
            withContext(ioDispatcher) {
                val contactsDTO = contactsUseCases.getContacts()

                _contacts.postValue(
                    BaseResult.resultOK(
                        contactsDTO.msg,
                        contactsDTO.contacts,
                        contactsDTO.code
                    )
                )
            }
        }
    }

    fun refresh(){
        getContacts()
    }

}