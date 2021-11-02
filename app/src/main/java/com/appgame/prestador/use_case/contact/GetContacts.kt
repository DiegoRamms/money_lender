package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.contact.ContactsResponse
import com.appgame.prestador.domain.repository.ContactsRepository

class GetContacts(private val contactsRepository: ContactsRepository) {
    suspend operator fun invoke(): ContactsResponse {
        return contactsRepository.getContacts()
    }
}