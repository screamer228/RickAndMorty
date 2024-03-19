package com.example.rickandmorty.domain.usecase.getCharacterById

import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharacterByIdUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharacterByIdUseCase {

    override fun getCharacterById(id: Int): Single<CharacterResultEntity> {
        return characterRepository.getCharacterById(id)
    }
}