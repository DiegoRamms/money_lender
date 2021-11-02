package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.AddContactRequest
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.repository.ContactsRepository

class AddContact(private val repository: ContactsRepository) {

    suspend operator fun invoke(addContactRequest: AddContactRequest): BaseResult<Contact> {
        return repository.addContact(addContactRequest)
    }

}