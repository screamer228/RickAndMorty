package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.models.character.CharacterOne
import com.example.rickandmorty.domain.models.character.CharacterResult

interface CharacterRepository {

    suspend fun getCharactersByPage(page: Int) : CharacterOne

    suspend fun getCharacterById(id : Int) : CharacterResult

}