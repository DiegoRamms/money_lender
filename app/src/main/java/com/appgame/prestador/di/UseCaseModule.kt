package com.appgame.prestador.di

import com.appgame.prestador.domain.repository.ContactsRepository
import com.appgame.prestador.domain.repository.UserRepository
import com.appgame.prestador.use_case.contact.*
import com.appgame.prestador.use_case.user.SearchUser
import com.appgame.prestador.use_case.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {


    @Provides
    @ViewModelScoped
    fun provideContactUseCases(contactsRepository: ContactsRepository): ContactUseCases {
        return  ContactUseCases(
            getContacts = GetContacts(contactsRepository),
            addContact = AddContact(contactsRepository),
            getPendingContacts = GetPendingContacts(contactsRepository),
            deleteContactRequest = DeleteContactRequest(contactsRepository),
            getContactsToAccept =  GetContactsToAccept(contactsRepository)
        )
    }

    @Provides
    @ViewModelScoped
    fun provideUserCases(userRepository: UserRepository): UserUseCases{
        return  UserUseCases(
            searchUser = SearchUser(userRepository)
        )
    }

}