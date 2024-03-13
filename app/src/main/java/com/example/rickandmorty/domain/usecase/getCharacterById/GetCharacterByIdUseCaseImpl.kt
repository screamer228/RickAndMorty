package com.example.rickandmorty.domain.usecase.getCharacterById

import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterByIdUseCase {

    override suspend fun getCharacterById(id: Int): CharacterResultEntity {
        return characterRepository.getCharacterById(id)
    }
}