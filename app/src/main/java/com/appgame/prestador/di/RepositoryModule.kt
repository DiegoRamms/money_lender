package com.appgame.prestador.di

import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.data.networkdatasource.*
import com.appgame.prestador.data.repository.*
import com.appgame.prestador.data.repository.impl.*
import com.appgame.prestador.domain.user.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginNetworkDataSource: LoginNetworkDataSource,
        localDataSource: UserLocalDataSource,
    ): LoginRepository {
        return LoginRepositoryImp(loginNetworkDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactsNetworkDataSource: ContactsNetworkDataSource): ContactsRepository {
        return ContactsRepositoryImp(contactsNetworkDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userNetworkDataSource: UserNetworkDataSource,
        userLocalDataSource: UserLocalDataSource
    ): UserRepository {
        return UserRepositoryImp(userNetworkDataSource, userLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideLoanRepository(loanNetworkDataSource: LoanNetworkDataSource): LoanRepository {
        return LoanRepositoryImp(loanNetworkDataSource)
    }

    @Provides
    @Singleton
    fun providePaymentRepository(
        paymentNetworkDataSource: PaymentNetworkDataSource
    ): PaymentRepository {
        return PaymentRepositoryImp(paymentNetworkDataSource)
    }

}