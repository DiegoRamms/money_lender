package com.appgame.prestador.domain.contact

data class Contact(
    val code: String,
    val email: String,
    val name: String,
    val idContact: String? = null
) {
}