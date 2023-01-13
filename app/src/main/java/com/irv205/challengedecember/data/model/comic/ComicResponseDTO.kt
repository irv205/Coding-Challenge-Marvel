package com.irv205.challengedecember.data.model.hero

import com.google.gson.annotations.SerializedName

data class ComicResponseDTO(
    @SerializedName("data")
    val data: DataComicDTO
)

data class DataComicDTO(
    @SerializedName("results")
    val result: List<ComicImageDTO>
)