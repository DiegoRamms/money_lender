package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.ContactIdRequest
import com.appgame.prestador.domain.repository.ContactsRepository

class AcceptContact(private val repository: ContactsRepository) {

    suspend operator fun invoke(contactIdRequest: ContactIdRequest): BaseResult<Contact>{
        return repository.acceptContact(contactIdRequest)
    }

}