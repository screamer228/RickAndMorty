package com.example.rickandmorty.data.network.models

import com.google.gson.annotations.SerializedName

data class CharactersResponseDTO(
//    val info: Info? = null,
    @SerializedName("results") val characterResults: List<CharacterResultDTO>? = null
)