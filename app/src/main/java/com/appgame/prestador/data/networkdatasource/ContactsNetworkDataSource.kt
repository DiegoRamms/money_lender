package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.domain.contact.*

interface ContactsNetworkDataSource {
    suspend fun getContacts(): ContactsResponse
    suspend fun addContact(addContactRequest: AddContactRequest): ContactResponse
    suspend fun getPendingContacts(): ContactsResponse
    suspend fun deleteContactRequest(contactIdRequest: ContactIdRequest): ContactResponse
    suspend fun getContactsToAccept(): ContactsResponse
    suspend fun acceptContact(contactIdRequest: ContactIdRequest): ContactResponse
}