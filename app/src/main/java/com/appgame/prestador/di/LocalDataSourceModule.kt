package com.appgame.prestador.di

import com.appgame.prestador.data.localdatasource.LoginLocalDataSource
import com.appgame.prestador.data.localdatasource.LoginLocalDataSourceImp
import com.appgame.prestador.data.localdatasource.preferences.LoginPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {


    @Provides
    @Singleton
    fun provideLoginLocalDataSource(loginPreferences: LoginPreferences): LoginLocalDataSource {
        return LoginLocalDataSourceImp(loginPreferences)
    }


}