package com.appgame.prestador.domain.contact

import com.appgame.prestador.domain.contact.ContactDTO

class Contacts(
    val msg: String,
    val status: String,
    val contactDTOS: List<ContactDTO>
) {
}