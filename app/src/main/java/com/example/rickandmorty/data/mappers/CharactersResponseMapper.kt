package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.models.CharactersResponseDTO
import com.example.rickandmorty.domain.entity.CharactersEntity

class CharactersResponseMapper {

    fun mapDataToDomain(characterResponse: CharactersResponseDTO): CharactersEntity {
        val characterResultMapper = CharacterResultMapper()
        val characterResultsEntityList =
            characterResponse.characterResults!!.map { characterResultDTO ->
                characterResultMapper.mapDataToDomain(characterResultDTO)
            }
        return CharactersEntity(characterResultsEntityList)
    }
}