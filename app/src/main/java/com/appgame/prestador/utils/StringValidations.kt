package com.appgame.prestador.utils



fun String.validateUserCode(): Boolean {
    return USER_CODE_PATTERN.matcher(this).matches()
}