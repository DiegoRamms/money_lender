package com.appgame.prestador.domain.repository

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.*

interface  ContactsRepository {

    suspend fun getContacts(): ContactsResponse
    suspend fun addContact(addContactRequest: AddContactRequest): BaseResult<Contact>
    suspend fun getPendingContacts(): BaseResult<List<Contact>>
    suspend fun deleteContactRequest(contactRequestRequest: DeleteContactRequestRequest): BaseResult<Contact>
    suspend fun getContactsToAccept(): BaseResult<List<Contact>>

}