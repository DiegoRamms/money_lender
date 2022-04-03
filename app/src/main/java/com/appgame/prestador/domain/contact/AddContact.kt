package com.appgame.prestador.domain.contact

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.contact.AddContactRequest
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.data.repository.ContactsRepository

class AddContact(private val repository: ContactsRepository) {

    suspend operator fun invoke(addContactRequest: AddContactRequest): BaseResult<Contact> {
        return repository.addContact(addContactRequest)
    }

}