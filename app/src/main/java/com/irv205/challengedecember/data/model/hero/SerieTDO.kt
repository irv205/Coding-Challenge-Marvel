package com.irv205.challengedecember.data.model.hero

import com.google.gson.annotations.SerializedName

data class SerieTDO(
    @SerializedName("items")
    val items: List<ItemSeriaDTO>
)

data class ItemSeriaDTO(
    @SerializedName("name")
    val name: String
)