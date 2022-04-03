package com.appgame.prestador.domain.contact

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.model.contact.ContactIdRequest
import com.appgame.prestador.data.repository.ContactsRepository

class DeleteContact(private val repository: ContactsRepository) {

    suspend operator fun invoke(contactIdRequest: ContactIdRequest): BaseResult<Contact>{
        return repository.deleteContactRequest(contactIdRequest)
    }

}