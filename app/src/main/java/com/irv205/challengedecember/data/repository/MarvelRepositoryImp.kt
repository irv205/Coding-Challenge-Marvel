package com.irv205.challengedecember.data.repository

import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.repository.MarvelRepository
import com.irv205.challengedecember.domain.service.MarvelNetworkDataSource
import javax.inject.Inject

class MarvelRepositoryImp @Inject constructor(
    private val networkDataSource: MarvelNetworkDataSource): MarvelRepository {

    override suspend fun getHeroes(): DomainResponse<List<Hero>> {
        return try {
            networkDataSource.getHeroes()
        } catch (e: Exception){
            DomainResponse.OnFailure(e.toString())
        }
    }
}