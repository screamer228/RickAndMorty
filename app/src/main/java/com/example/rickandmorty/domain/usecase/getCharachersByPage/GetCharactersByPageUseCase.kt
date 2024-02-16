package com.example.rickandmorty.domain.usecase.getCharachersByPage

import com.example.rickandmorty.domain.entity.CharactersEntity

interface GetCharactersByPageUseCase {

    suspend fun getCharactersByPage(page: Int): CharactersEntity
}