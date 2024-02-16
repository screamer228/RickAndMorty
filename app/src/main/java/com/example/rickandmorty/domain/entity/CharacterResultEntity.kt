package com.example.rickandmorty.domain.entity

import com.example.rickandmorty.data.network.models.Location
import com.example.rickandmorty.data.network.models.Origin

data class CharacterResultEntity(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
)
