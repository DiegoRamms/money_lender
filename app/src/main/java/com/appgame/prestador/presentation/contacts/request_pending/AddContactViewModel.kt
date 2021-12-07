package com.appgame.prestador.presentation.contacts.request_pending

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appgame.prestador.domain.contact.AddContactRequest
import com.appgame.prestador.di.IODispatcher
import com.appgame.prestador.di.MainDispatcher
import com.appgame.prestador.use_case.contact.ContactUseCases
import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.user.SearchUserRequest
import com.appgame.prestador.domain.user.User
import com.appgame.prestador.use_case.user.UserUseCases
import com.appgame.prestador.utils.validateUserCode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class AddContactViewModel @Inject constructor(
    private val contactUseCases: ContactUseCases,
    @IODispatcher private val ioDispatcher: CoroutineContext,
    @MainDispatcher private val mainDispatcher: CoroutineContext,
    private val userUseCases: UserUseCases
) : ViewModel() {

    private val _userFound = MutableLiveData<BaseResult<User>>()
    val userFound get() = _userFound
    private val _validCode = MutableLiveData<Boolean>()
    val validCode get() = _validCode
    private val _dismissDialog = MutableLiveData<Boolean>()
    val dismissDialog get() = _dismissDialog
    private val _contactAdded = MutableLiveData<BaseResult<Contact>>()
    val contactAdded get() = _contactAdded
    private val _pendingContacts = MutableLiveData<BaseResult<List<Contact>>>()
    val pendingContacts get() = _pendingContacts
    private val _contactDeleted = MutableLiveData<BaseResult<Contact>>()
    val contactDeleted get() = _contactDeleted

    fun setDismissFalse() {
        _dismissDialog.value = false
    }

    fun searchUser(searchUserRequest: SearchUserRequest) {
        _userFound.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _userFound.value = BaseResult.resultBad()
            _dismissDialog.value = true
        }) {

            val userResponse =
                withContext(ioDispatcher) { userUseCases.searchUser(searchUserRequest) }
            _userFound.postValue(userResponse)
            _dismissDialog.value = true
        }

    }

    fun validateUserCode(userCode: String) {
        _validCode.value = userCode.validateUserCode()
    }

    fun getPendingContacts() {
        _pendingContacts.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _pendingContacts.value = BaseResult.resultBad()
            _dismissDialog.value = true
        }) {
            withContext(ioDispatcher) { _pendingContacts.postValue(contactUseCases.getPendingContacts()) }
            _dismissDialog.value = true
        }
    }

    fun addContact(addContactRequest: AddContactRequest) {
        _contactAdded.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, _ ->
            _contactAdded.value = BaseResult.resultBad()
            _dismissDialog.value = true
        }) {
            withContext(ioDispatcher) {
                _contactAdded.postValue(
                    contactUseCases.addContact(
                        addContactRequest
                    )
                )
            }
            _dismissDialog.value = true
            getPendingContacts()
        }
    }


    fun deleteContact(contactIdRequest: ContactIdRequest) {
        _contactDeleted.value = BaseResult.resultLoading()
        viewModelScope.launch(mainDispatcher + CoroutineExceptionHandler { _, tr ->
            _contactDeleted.value = BaseResult.resultBad(tr.message + ",")
            _dismissDialog.value = true

        }) {
            withContext(ioDispatcher) {
                _contactDeleted.postValue(contactUseCases.deleteContact(contactIdRequest))
            }
            _dismissDialog.value = true
            getPendingContacts()

        }
    }


}