package com.example.rickandmorty.domain.usecase.getCharacterById

import com.example.rickandmorty.domain.entity.CharacterResultEntity
import io.reactivex.rxjava3.core.Single

interface GetCharacterByIdUseCase {

    fun getCharacterById(id: Int): Single<CharacterResultEntity>

}