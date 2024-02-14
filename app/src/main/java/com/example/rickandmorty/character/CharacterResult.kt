package com.example.rickandmorty.character

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class CharacterResult(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    @SerializedName("type") val type: String,
    val url: String
)