package com.irv205.challengedecember.data.networkdatasource.service

import com.irv205.challengedecember.data.model.DataResponseDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String
    ): DataResponseDTO
}