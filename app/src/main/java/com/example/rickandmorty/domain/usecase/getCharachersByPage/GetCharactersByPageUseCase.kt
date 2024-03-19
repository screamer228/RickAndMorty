package com.example.rickandmorty.domain.usecase.getCharachersByPage

import com.example.rickandmorty.domain.entity.CharactersEntity
import io.reactivex.rxjava3.core.Single

interface GetCharactersByPageUseCase {

    fun getCharactersByPage(page: Int): Single<CharactersEntity>
}