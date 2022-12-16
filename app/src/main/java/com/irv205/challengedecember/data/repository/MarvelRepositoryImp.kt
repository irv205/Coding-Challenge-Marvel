package com.irv205.challengedecember.data.repository

import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series
import com.irv205.challengedecember.domain.repository.MarvelRepository
import com.irv205.challengedecember.domain.service.MarvelNetworkDataSource
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val networkDataSource: MarvelNetworkDataSource): MarvelRepository {

    override suspend fun getHeroes(limit: Int): DomainResponse<List<Hero>> {
        return try {
            networkDataSource.getHeroes(limit)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.toString())
        }
    }

    override suspend fun getSeries(character: Int): DomainResponse<List<Series>> {
        return try {
            networkDataSource.getSeries(character)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.toString())
        }
    }

    override suspend fun getComics(character: Int): DomainResponse<List<Comics>> {
        return try {
            networkDataSource.getComics(character)
        } catch (e: Exception){
            DomainResponse.OnFailure(e.toString())
        }
    }

}