package com.appgame.prestador.domain.user

sealed class FormError(val description: String? = null){
    //class OK: FormError()
    open class Error(description: String): FormError(description)
}

//Form Create Loan
class AmountError(description: String): FormError.Error(description)
class DateError(description: String) : FormError.Error(description)
class TypeInterest(description: String) : FormError.Error(description)
