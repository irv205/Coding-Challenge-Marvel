package com.irv205.challengedecember.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import javax.inject.Qualifier


val dispatcherModule = module {
    singleOf(::providerDispatcherIo){named("IO")}
    singleOf(::providerDispatcherMain){named("MAIN")}

}

fun providerDispatcherIo(): CoroutineDispatcher = Dispatchers.IO

fun providerDispatcherMain(): CoroutineDispatcher = Dispatchers.Main
