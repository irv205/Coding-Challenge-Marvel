package com.irv205.challengedecember.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule{

    @Provides
    @IODispatcher
    fun providerDispatcherIo(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDisplatcher
    fun providerDispatcherMain(): CoroutineDispatcher = Dispatchers.Main

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDisplatcher