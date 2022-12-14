package com.irv205.challengedecember.domain.service

import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Hero

interface MarvelNetworkDataSource {
    suspend fun getHeroes(): DomainResponse<List<Hero>>
}