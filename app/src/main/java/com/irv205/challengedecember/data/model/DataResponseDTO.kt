package com.irv205.challengedecember.data.model

import com.google.gson.annotations.SerializedName

data class DataResponseDTO(
    @SerializedName("data")
    val data: DataDTO
)

data class DataDTO(
    @SerializedName("results")
    val result: List<HeroDTO>
)