package com.appgame.prestador.domain.loan

class LoanUseCases(
    val getLoansByContactId: GetLoansByContactId,
    val createLoan: CreateLoan,
    val acceptLoan: AcceptLoan,
    val deleteLoan: DeleteLoan
)