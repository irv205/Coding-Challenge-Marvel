package com.irv205.challengedecember.domain.repository

import com.irv205.challengedecember.domain.DomainResponse
import com.irv205.challengedecember.domain.model.Hero


interface MarvelRepository {
    suspend fun getHeroes(): DomainResponse<List<Hero>>
}