package com.example.rickandmorty.data.repository

import com.example.rickandmorty.data.mappers.CharacterResultMapper
import com.example.rickandmorty.data.mappers.CharactersResponseMapper
import com.example.rickandmorty.data.network.models.Location
import com.example.rickandmorty.data.network.models.Origin
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterApi: CharacterApi,
    private val characterResultMapper: CharacterResultMapper,
    private val charactersResponseMapper: CharactersResponseMapper
) : CharacterRepository {

    override fun getCharactersByPage(page: Int): Single<CharactersEntity> {
        return characterApi.getCharactersByPage(page)
            .map {
                if (it.isSuccessful) {
                    charactersResponseMapper.mapDataToDomain(it.body()!!)
                } else {
                    throw Throwable(it.errorBody().toString())
                }
            }
    }

    override fun getCharacterById(id: Int): Single<CharacterResultEntity> {
        return characterApi.getCharacterById(id)
            .map {
                if (it.isSuccessful) {
                    characterResultMapper.mapDataToDomain(it.body()!!)
                } else {
                    CharacterResultEntity(
                        created = "",
                        gender = "",
                        id = 0,
                        image = "",
                        location = Location("", ""),
                        name = "",
                        origin = Origin("", ""),
                        species = "",
                        status = "",
                        type = "",
                    )
                }
            }
    }

}