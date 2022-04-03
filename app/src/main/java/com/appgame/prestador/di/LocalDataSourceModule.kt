package com.appgame.prestador.di

import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.data.localdatasource.UserLocalDataSourceImp
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
    fun provideLoginLocalDataSource(loginPreferences: LoginPreferences): UserLocalDataSource {
        return UserLocalDataSourceImp(loginPreferences)
    }


}