package com.appgame.prestador.domain.contact

import com.appgame.prestador.model.BaseResult
import com.appgame.prestador.model.contact.Contact
import com.appgame.prestador.data.repository.ContactsRepository

class GetContacts(private val contactsRepository: ContactsRepository) {
    suspend operator fun invoke(): BaseResult<List<Contact>> {
        return contactsRepository.getContacts()
    }
}