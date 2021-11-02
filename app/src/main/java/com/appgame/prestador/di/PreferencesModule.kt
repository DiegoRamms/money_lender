package com.appgame.prestador.di

import android.content.Context
import android.content.SharedPreferences
import com.appgame.prestador.data.localdatasource.preferences.AppPreferences
import com.appgame.prestador.data.localdatasource.preferences.LoginPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {


    @Provides
    @Singleton
    fun provideAppPreferences(@ApplicationContext context: Context): AppPreferences{
        return AppPreferences(context)
    }

    @Provides
    @Singleton
    fun provideSharePreferences(appPreferences: AppPreferences): SharedPreferences{
        return appPreferences.sharedPreferences
    }

    @Provides
    @Singleton
    fun provideLoginPreferences(sharedPreferences: SharedPreferences): LoginPreferences {
        return  LoginPreferences(sharedPreferences)
    }


}