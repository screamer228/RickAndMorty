package com.example.rickandmorty.domain.usecase.getCharachersByPage

import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersByPageUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharactersByPageUseCase {

    override suspend fun getCharactersByPage(page: Int) : CharactersEntity {
        return characterRepository.getCharactersByPage(page)
    }

}