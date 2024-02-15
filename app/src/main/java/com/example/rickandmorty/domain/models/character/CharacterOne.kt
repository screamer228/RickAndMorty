package com.example.rickandmorty.domain.models.character

import com.google.gson.annotations.SerializedName

data class CharacterOne(
    val info: Info,
    @SerializedName("results") val characterResults: List<CharacterResult>? = null
)