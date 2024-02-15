package com.example.rickandmorty.data.repository

import com.example.rickandmorty.domain.models.character.CharacterOne
import com.example.rickandmorty.domain.models.character.CharacterResult
import com.example.rickandmorty.domain.models.character.Info
import com.example.rickandmorty.domain.models.character.Location
import com.example.rickandmorty.domain.models.character.Origin
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.network.CharacterApi
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi
): CharacterRepository {

    override suspend fun getCharactersByPage(page: Int): CharacterOne {
        val response = characterApi.getCharactersByPage(page)
        if (response.isSuccessful){
            val characterResult = response.body()
            return CharacterOne(
                info = characterResult?.info ?: Info(0, "", 0, ""),
                characterResults = characterResult?.characterResults ?: listOf()
            )
        }
        return CharacterOne(
            info = Info(0, "", 0, ""),
            characterResults = listOf(
                CharacterResult(
                    "1",
                    listOf(),
                    "1",
                    0,
                    "1",
                    Location("1", "1"),
                    "1",
                    Origin("1", "1"),
                    "1",
                    "1",
                    "1",
                    "1"
                )
            )
        )
    }

    override suspend fun getCharacterById(id: Int): CharacterResult {
        val response = characterApi.getCharacterById(id)
        if (response.isSuccessful){
            val characterByIdResult = response.body()
            return CharacterResult(
                created = characterByIdResult?.created ?: "",
                episode = characterByIdResult?.episode ?: listOf(),
                gender = characterByIdResult?.gender ?: "",
                id = characterByIdResult?.id ?: 0,
                image = characterByIdResult?.image ?: "",
                location = characterByIdResult?.location ?: Location("",""),
                name = characterByIdResult?.name ?: "",
                origin = characterByIdResult?.origin ?: Origin("",""),
                species = characterByIdResult?.species ?: "",
                status = characterByIdResult?.status ?: "",
                type = characterByIdResult?.type ?: "",
                url = characterByIdResult?.url ?: ""
            )
        }
        return CharacterResult(
            created = "",
            episode = listOf(),
            gender = "",
            id = 0,
            image = "",
            location = Location("",""),
            name = "",
            origin = Origin("",""),
            species = "",
            status = "",
            type = "",
            url = ""

        )
    }

    companion object {
        const val PAGE = 1
    }
}