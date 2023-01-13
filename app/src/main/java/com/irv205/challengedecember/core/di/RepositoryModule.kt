package com.irv205.challengedecember.core.di

import com.irv205.challengedecember.data.repository.MarvelRepositoryImp
import com.irv205.challengedecember.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::MarvelRepositoryImp){ bind<MarvelRepository>()}
}