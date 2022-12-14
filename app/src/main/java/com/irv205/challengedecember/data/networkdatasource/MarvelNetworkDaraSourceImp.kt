package com.irv205.challengedecember.data.networkdatasource

import com.irv205.challengedecember.BuildConfig
import com.irv205.challengedecember.data.mapper.toDomainModel
import com.irv205.challengedecember.data.networkdatasource.service.MarvelService
import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.service.MarvelNetworkDataSource
import javax.inject.Inject

class MarvelNetworkDaraSourceImp @Inject constructor(private val service: MarvelService): MarvelNetworkDataSource {
    override suspend fun getHeroes(): DomainResponse<List<Hero>> {
        val response = service.getCharacters(BuildConfig.TS, BuildConfig.API_KEY, BuildConfig.HASH)
        return DomainResponse.Success(response.toDomainModel())
    }

}