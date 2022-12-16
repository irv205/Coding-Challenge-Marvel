package com.irv205.challengedecember.data.model.hero

data class HeroDTO(
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailDTO?,
    val comics: ComicDTO?,
    val series: SerieTDO?,
    val urls: List<UrlDTO?>,
    val id: Int?,
)