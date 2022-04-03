package com.appgame.prestador.di

import com.appgame.prestador.data.repository.*
import com.appgame.prestador.domain.contact.*
import com.appgame.prestador.domain.loan.*
import com.appgame.prestador.domain.login.Login
import com.appgame.prestador.domain.login.LoginUseCase
import com.appgame.prestador.domain.login.Logout
import com.appgame.prestador.domain.payment.CreatePayment
import com.appgame.prestador.domain.payment.GetLoanPaymentDetail
import com.appgame.prestador.domain.payment.PaymentUseCases
import com.appgame.prestador.domain.user.GetCurrentUserId
import com.appgame.prestador.domain.user.SearchUser
import com.appgame.prestador.domain.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository): LoginUseCase{
        return LoginUseCase(
            Login(loginRepository),
            Logout(loginRepository)
        )
    }

    @Provides
    @Singleton
    fun provideContactUseCases(contactsRepository: ContactsRepository): ContactUseCases {
        return ContactUseCases(
            getContacts = GetContacts(contactsRepository),
            addContact = AddContact(contactsRepository),
            getPendingContacts = GetPendingContacts(contactsRepository),
            deleteContact = DeleteContact(contactsRepository),
            getContactsToAccept = GetContactsToAccept(contactsRepository),
            acceptContact = AcceptContact(contactsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideUserCases(userRepository: UserRepository): UserUseCases {
        return UserUseCases(
            searchUser = SearchUser(userRepository),
            getCurrentUserId = GetCurrentUserId(userRepository)
        )
    }

    @Provides
    @Singleton
    fun provideLoanUseCases(loanRepository: LoanRepository): LoanUseCases {
        return LoanUseCases(
            getLoansByContactId = GetLoansByContactId(loanRepository),
            createLoan = CreateLoan(loanRepository),
            acceptLoan = AcceptLoan(loanRepository),
            deleteLoan = DeleteLoan(loanRepository)
        )
    }

    @Provides
    @Singleton
    fun providePaymentUseCases(paymentRepository: PaymentRepository): PaymentUseCases {
        return PaymentUseCases(
            getLoanPaymentDetail = GetLoanPaymentDetail(paymentRepository),
            createPayment = CreatePayment(paymentRepository)
        )
    }

}