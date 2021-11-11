package com.appgame.prestador.data.networkdatasource

import com.appgame.prestador.data.networkdatasource.service.ContactsService
import com.appgame.prestador.domain.contact.*
import javax.inject.Inject

class ContactsNetworkDataSourceImp @Inject constructor(private val service: ContactsService): ContactsNetworkDataSource {
    override suspend fun getContacts(): ContactsResponse {
        return service.getContacts()
    }

    override suspend fun addContact(addContactRequest: AddContactRequest): ContactResponse {
        return service.addContact(addContactRequest)
    }

    override suspend fun getPendingContacts(): ContactsResponse {
        return service.getPendingContacts()
    }

    override suspend fun deleteContactRequest(idContactRequest: IdContactRequest): ContactResponse {
        return service.deleteContactRequest(idContactRequest)
    }

    override suspend fun getContactsToAccept(): ContactsResponse {
        return service.getContactsToAccept()
    }

    override suspend fun acceptContact(idContactRequest: IdContactRequest): ContactResponse {
        return service.acceptContact(idContactRequest)
    }
}