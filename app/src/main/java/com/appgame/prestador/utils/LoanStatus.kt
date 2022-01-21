package com.appgame.prestador.utils

enum class LoanStatus(val value: String) {
    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    FINALIZED("FINALIZED")
}


fun mapStatus(status: String): String{
    return when (status) {
        LoanStatus.PENDING.value -> "Pendiente"
        LoanStatus.IN_PROGRESS.value -> "En progreso"
        LoanStatus.FINALIZED.value -> "Finalizado"
        else -> ""
    }
}