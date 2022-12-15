package com.irv205.challengedecember.data.networkdatasource.service

import com.irv205.challengedecember.data.model.hero.ComicResponseDTO
import com.irv205.challengedecember.data.model.hero.DataResponseDTO
import com.irv205.challengedecember.data.model.hero.SerieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(): DataResponseDTO

    @GET("characters/{character}/series")
    suspend fun getSeries(@Path("character")  character : Int): SerieResponseDTO

    @GET("characters/{character}/comics")
    suspend fun getComics(@Path("character")  character : Int): ComicResponseDTO
}