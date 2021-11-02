package com.appgame.prestador.di

import com.appgame.prestador.data.localdatasource.LoginLocalDataSource
import com.appgame.prestador.data.networkdatasource.ContactsNetworkDataSource
import com.appgame.prestador.data.networkdatasource.LoginNetworkDataSource
import com.appgame.prestador.data.networkdatasource.UserNetworkDataSource
import com.appgame.prestador.domain.repository.ContactsRepository
import com.appgame.prestador.data.repository.ContactsRepositoryImp
import com.appgame.prestador.domain.repository.LoginRepository
import com.appgame.prestador.data.repository.LoginRepositoryImp
import com.appgame.prestador.data.repository.UserRepositoryImp
import com.appgame.prestador.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(
        loginNetworkDataSource: LoginNetworkDataSource,
        localDataSource: LoginLocalDataSource,
        @IODispatcher ioDispatcher: CoroutineContext
    ): LoginRepository {
        return LoginRepositoryImp(loginNetworkDataSource, localDataSource )
    }

    @Provides
    @ViewModelScoped
    fun provideContactRepository(contactsNetworkDataSource: ContactsNetworkDataSource): ContactsRepository {
       return  ContactsRepositoryImp(contactsNetworkDataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideUserRepository(userNetworkDataSource: UserNetworkDataSource): UserRepository{
        return UserRepositoryImp(userNetworkDataSource)
    }

}