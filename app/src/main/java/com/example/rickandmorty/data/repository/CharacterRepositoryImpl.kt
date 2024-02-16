package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.mappers.CharacterResultMapper
import com.example.rickandmorty.data.mappers.CharactersResponseMapper
import com.example.rickandmorty.data.network.models.Location
import com.example.rickandmorty.data.network.models.Origin
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi,
    private val characterResultMapper: CharacterResultMapper,
    private val charactersResponseMapper: CharactersResponseMapper
): CharacterRepository {

    override suspend fun getCharactersByPage(page: Int): CharactersEntity {
        val response = characterApi.getCharactersByPage(page)
        if (response.isSuccessful){
            val characterResult = response.body()
            return charactersResponseMapper.mapDataToDomain(characterResult!!)
        }
        return CharactersEntity(
            characterResults = listOf(
                CharacterResultEntity(
                    "1",
                    "1",
                    0,
                    "1",
                    Location("1", "1"),
                    "1",
                    Origin("1", "1"),
                    "1",
                    "1",
                    "1",
                )
            )
        )
    }

    override suspend fun getCharacterById(id: Int): CharacterResultEntity {
        val response = characterApi.getCharacterById(id)
        if (response.isSuccessful){
            val characterByIdResult = response.body()
            return characterResultMapper.mapDataToDomain(characterByIdResult!!)
        }
        return CharacterResultEntity(
            created = "",
            gender = "",
            id = 0,
            image = "",
            location = Location("",""),
            name = "",
            origin = Origin("",""),
            species = "",
            status = "",
            type = "",
        )
    }

}