package com.irv205.challengedecember.data.model.hero

import com.google.gson.annotations.SerializedName
import com.irv205.challengedecember.domain.model.Hero

data class DataResponseDTO(
    @SerializedName("data")
    val data: DataDTO
)

data class DataDTO(
    @SerializedName("results")
    val result: List<HeroDTO>
)