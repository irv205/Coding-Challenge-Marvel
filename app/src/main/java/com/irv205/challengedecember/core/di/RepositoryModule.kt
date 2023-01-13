package com.irv205.challengedecember.core.di

import com.irv205.challengedecember.data.repository.MarvelRepositoryImp
import com.irv205.challengedecember.domain.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideMarvelRepository(marvelRepositoryImp: MarvelRepositoryImp): MarvelRepository
}