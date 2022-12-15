package com.irv205.challengedecember.data.mapper

import com.irv205.challengedecember.data.model.hero.*
import com.irv205.challengedecember.domain.model.Comics
import com.irv205.challengedecember.domain.model.Hero
import com.irv205.challengedecember.domain.model.Series

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
        thumbnail?.path?.replace("http", "https")+"."+thumbnail?.extension,
        comics?.items?.map { it.name } ?: emptyList(),
        series?.items?.map { it.name } ?: emptyList(),
        urls.map { it?.url ?: "" }
    )
}

fun ComicResponseDTO.toDomainModel(): List<Comics> {
    val result = data.result
    println(result)
    return this.data.result.map { it.toDomain() }
}

fun ComicImageDTO.toDomain(): Comics {

    val description = description ?: "No description"

    return Comics(
        name?: "",
        description,
        thumbnail?.path?.replace("http", "https")+"."+thumbnail?.extension,
        comics?.items?.map { it.name } ?: emptyList(),
        series?.items?.map { it.name } ?: emptyList(),
        urls.map { it?.url ?: "" }
    )
}


fun SerieResponseDTO.toDomainModel(): List<Series> {
    val result = data.result
    println(result)
    return this.data.result.map { it.toDomain() }
}

fun SerieImageDTO.toDomain(): Series{

    val description = description ?: "No description"

    return Series(
        name?: "",
        description,
        thumbnail?.path?.replace("http", "https")+"."+thumbnail?.extension,
        comics?.items?.map { it.name } ?: emptyList(),
        series?.items?.map { it.name } ?: emptyList(),
        urls.map { it?.url ?: "" }
    )
}

