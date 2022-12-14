package com.irv205.challengedecember.di


import com.irv205.challengedecember.data.networkdatasource.MarvelNetworkDaraSourceImp
import com.irv205.challengedecember.domain.service.MarvelNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideMarvelNetworkSource(marvelNetworkDataSourceImp: MarvelNetworkDaraSourceImp): MarvelNetworkDataSource
}