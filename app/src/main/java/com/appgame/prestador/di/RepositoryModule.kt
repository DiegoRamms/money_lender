package com.appgame.prestador.di

import com.appgame.prestador.data.localdatasource.LoginLocalDataSource
import com.appgame.prestador.data.networkdatasource.*
import com.appgame.prestador.data.repository.*
import com.appgame.prestador.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(
        loginNetworkDataSource: LoginNetworkDataSource,
        localDataSource: LoginLocalDataSource,
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

    @Provides
    @ViewModelScoped
    fun provideLoanRepository(loanNetworkDataSource: LoanNetworkDataSource): LoanRepository{
        return LoanRepositoryImp(loanNetworkDataSource)
    }

    @Provides
    @ViewModelScoped
    fun providePaymentRepository(paymentNetworkDataSource: PaymentNetworkDataSource): PaymentRepository{
        return PaymentRepositoryImp(paymentNetworkDataSource)
    }

}