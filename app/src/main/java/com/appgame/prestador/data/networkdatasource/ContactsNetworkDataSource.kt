package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.contact.*

interface ContactsNetworkDataSource {
    suspend fun getContacts(): ContactsResponse
    suspend fun addContact(addContactRequest: AddContactRequest): ContactResponse
    suspend fun getPendingContacts(): ContactsResponse
    suspend fun deleteContactRequest(contactRequestRequest: DeleteContactRequestRequest): ContactResponse
    suspend fun getContactsToAccept(): ContactsResponse
}