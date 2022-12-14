package com.irv205.challengedecember.data.mapper

import android.util.Log
import com.irv205.challengedecember.data.model.DataResponseDTO
import com.irv205.challengedecember.data.model.HeroDTO
import com.irv205.challengedecember.domain.model.Hero

fun DataResponseDTO.toDomainModel(): List<Hero> {
    val result = data.result
    println(result)
    return this.data.result.map { it.toDomain() }
}

fun HeroDTO.toDomain(): Hero{

    val description = description ?: "No description"

    return Hero(
        name?: "",
        description,
        thumbnail?.extension?.replace("http", "https")+thumbnail?.path,
        comics?.items?.map { it.name } ?: emptyList(),
        series?.items?.map { it.name } ?: emptyList(),
        urls.map { it?.url ?: "" }
    )
}