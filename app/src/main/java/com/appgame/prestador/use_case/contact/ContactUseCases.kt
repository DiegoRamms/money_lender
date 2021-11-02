package com.appgame.prestador.use_case.contact

import com.appgame.prestador.domain.repository.ContactsRepository

class ContactUseCases(
    val getContacts: GetContacts,
    val addContact: AddContact,
    val getPendingContacts: GetPendingContacts,
    val deleteContactRequest: DeleteContactRequest,
    val getContactsToAccept: GetContactsToAccept
)