package com.irv205.challengedecember.domain.service

import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series

interface MarvelNetworkDataSource {
    suspend fun getHeroes(limit: Int,name:String): DomainResponse<List<Hero>>
    suspend fun getSeries(character: Int): DomainResponse<List<Series>>
    suspend fun getComics(character: Int): DomainResponse<List<Comics>>
}