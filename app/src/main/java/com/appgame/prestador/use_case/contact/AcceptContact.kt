package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.contact.IdContactRequest
import com.appgame.prestador.domain.repository.ContactsRepository

class AcceptContact(private val repository: ContactsRepository) {

    suspend operator fun invoke(idContactRequest: IdContactRequest): BaseResult<Contact>{
        return repository.acceptContact(idContactRequest)
    }

}