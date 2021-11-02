package com.appgame.prestador.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(ViewModelComponent::class)
object DispatcherModule {

    @Provides
    @IODispatcher
    fun provideIODispatcher(): CoroutineContext {
        return (Dispatchers.IO)
    }
    @Provides
    @MainDispatcher
    fun provideMainDispatcher(): CoroutineContext {
        return (Dispatchers.Main)
    }
    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineContext {
        return (Dispatchers.Default)
    }


}

