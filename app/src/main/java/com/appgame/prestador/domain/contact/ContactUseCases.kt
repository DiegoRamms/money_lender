package com.appgame.prestador.domain.contact

class ContactUseCases(
    val getContacts: GetContacts,
    val addContact: AddContact,
    val getPendingContacts: GetPendingContacts,
    val deleteContact: DeleteContact,
    val getContactsToAccept: GetContactsToAccept,
    val acceptContact: AcceptContact
)