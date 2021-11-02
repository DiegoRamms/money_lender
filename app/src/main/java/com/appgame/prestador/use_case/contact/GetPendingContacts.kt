package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.BaseResult
import com.appgame.prestador.domain.contact.Contact
import com.appgame.prestador.domain.repository.ContactsRepository

class GetPendingContacts(private val repository: ContactsRepository) {
    suspend operator fun invoke(): BaseResult<List<Contact>>{
        return repository.getPendingContacts()
    }
}