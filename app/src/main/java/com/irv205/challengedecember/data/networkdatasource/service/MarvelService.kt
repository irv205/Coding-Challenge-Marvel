package com.irv205.challengedecember.data.networkdatasource.service

import com.irv205.challengedecember.data.model.hero.ComicResponseDTO
import com.irv205.challengedecember.data.model.hero.DataResponseDTO
import com.irv205.challengedecember.data.model.hero.SerieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(@Query("limit") limit: Int): DataResponseDTO

    @GET("characters/{character}/series")
    suspend fun getSeries(@Path("character")  character : Int): SerieResponseDTO

    @GET("characters/{character}/comics")
    suspend fun getComics(@Path("character")  character : Int): ComicResponseDTO
}