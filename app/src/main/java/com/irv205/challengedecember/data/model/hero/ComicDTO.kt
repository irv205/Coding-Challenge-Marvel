package com.irv205.challengedecember.data.model.hero

import com.google.gson.annotations.SerializedName

data class ComicDTO(
    val items: List<ItemComicDTO>
)

data class ItemComicDTO(
    @SerializedName("name")
    val name: String
)