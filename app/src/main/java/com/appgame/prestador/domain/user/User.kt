package com.appgame.prestador.domain.user

data class User (
    val status: Boolean,
    val name: String,
    val email: String,
    val code: String,
    val createdAt: String,
    val uid: String
)
