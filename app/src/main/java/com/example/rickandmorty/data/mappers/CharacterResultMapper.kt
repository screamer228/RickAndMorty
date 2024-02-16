package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.models.CharacterResultDTO
import com.example.rickandmorty.data.network.models.Location
import com.example.rickandmorty.data.network.models.Origin
import com.example.rickandmorty.domain.entity.CharacterResultEntity

class CharacterResultMapper {

    fun mapDataToDomain(characterResult : CharacterResultDTO) : CharacterResultEntity {
        return CharacterResultEntity(
            created = characterResult.created ?: "",
            gender = characterResult.gender ?: "",
            id = characterResult.id ?: 0,
            image = characterResult.image ?: "",
            location = characterResult.location ?: Location("", ""),
            name = characterResult.name ?: "",
            origin = characterResult.origin ?: Origin("", ""),
            species = characterResult.species ?: "",
            status = characterResult.status ?: "",
            type = characterResult.type ?: "",
        )
    }
}