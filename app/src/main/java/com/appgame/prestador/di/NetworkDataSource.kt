package com.appgame.prestador.di

import com.appgame.prestador.data.networkdatasource.*
import com.appgame.prestador.data.networkdatasource.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkDataSource {

    @Provides
    @Singleton
    fun provideLoginNetworkDataSource(service: LoginService): LoginNetworkDataSource {
        return LoginNetworkDataSourceImp(service)
    }


    @Provides
    @Singleton
    fun provideContactsNetworkDataSource(service: ContactsService): ContactsNetworkDataSource {
        return ContactsNetworkDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideUserNetworkDataSource(service: UserService): UserNetworkDataSource {
        return UserNetworkDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideLoanNetworkDataSource(service: LoanService): LoanNetworkDataSource {
        return LoanNetworkDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun providePaymentNetworkDataSource(service: PaymentService): PaymentNetworkDataSource{
        return PaymentNetworkDataSourceImp(service)
    }

    @Provides
    @Singleton
    fun provideMainDetailNetworkDataSource(service: MainDetailService): MainDetailDetailNetworkDataSource{
        return MainDetailDetailNetworkDataSourceImp(service)
    }


}