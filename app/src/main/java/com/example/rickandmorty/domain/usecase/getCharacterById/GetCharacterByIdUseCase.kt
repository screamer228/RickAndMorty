package com.example.rickandmorty.domain.usecase.getCharacterById

import com.example.rickandmorty.domain.entity.CharacterResultEntity

interface GetCharacterByIdUseCase {

    suspend fun getCharacterById(id : Int) : CharacterResultEntity

}