package com.example.rickandmorty.domain.usecase.getCharachersByPage

import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetCharactersByPageUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository
) : GetCharactersByPageUseCase {

    override fun getCharactersByPage(page: Int): Single<CharactersEntity> {
        return characterRepository.getCharactersByPage(page)
//            try {
//                val characters = characterRepository.getCharactersByPage(page)
//                subscriber.onSuccess(characters)
//            } catch (e: Exception) {
//                subscriber.onError(e)
//            }
//        }
    }
}