package com.irv205.challengedecember.data.networkdatasource

import com.irv205.challengedecember.BuildConfig
import com.irv205.challengedecember.data.mapper.toDomainModel
import com.irv205.challengedecember.data.networkdatasource.service.MarvelService
import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
import com.irv205.challengedecember.domain.service.MarvelNetworkDataSource
import javax.inject.Inject

class MarvelNetworkDaraSourceImp @Inject constructor(private val service: MarvelService): MarvelNetworkDataSource {
    override suspend fun getHeroes(limit: Int,name: String ): DomainResponse<List<Hero>> {
        val response = service.getCharacters(limit,name)
        return DomainResponse.Success(response.toDomainModel())
    }

    override suspend fun getSeries(character: Int): DomainResponse<List<Series>> {
        val response = service.getSeries(character)
        return DomainResponse.Success(response.toDomainModel())
    }

    override suspend fun getComics(character: Int): DomainResponse<List<Comics>> {
        val response = service.getComics(character)
        return DomainResponse.Success(response.toDomainModel())
    }

}