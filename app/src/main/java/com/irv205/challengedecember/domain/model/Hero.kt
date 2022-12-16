package com.irv205.challengedecember.domain.model


data class Hero(
    val name: String,
    val description: String?,
    val thumbnail: String,
    val comic: List<String>,
    val series: List<String>,
    val url: List<String>,
    val id: Int
)