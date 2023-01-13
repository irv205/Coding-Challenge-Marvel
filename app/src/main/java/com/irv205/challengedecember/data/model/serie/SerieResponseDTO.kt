package com.irv205.challengedecember.data.model.hero

import com.google.gson.annotations.SerializedName

data class SerieResponseDTO(
    @SerializedName("data")
    val data: DataSerieDTO
)

data class DataSerieDTO(
    @SerializedName("results")
    val result: List<SerieImageDTO>
)