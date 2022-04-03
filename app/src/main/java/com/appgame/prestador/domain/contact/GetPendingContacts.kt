package com.appgame.prestador.domain.contact

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.data.repository.ContactsRepository

class GetPendingContacts(private val repository: ContactsRepository) {
    suspend operator fun invoke(): BaseResult<List<Contact>>{
        return repository.getPendingContacts()
    }
}