package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity

interface CharacterRepository {

    suspend fun getCharactersByPage(page: Int): CharactersEntity

    suspend fun getCharacterById(id: Int): CharacterResultEntity

}