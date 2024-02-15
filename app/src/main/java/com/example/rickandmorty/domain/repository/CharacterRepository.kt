package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.data.network.models.CharacterOne
import com.example.rickandmorty.data.network.models.CharacterResult

interface CharacterRepository {

    suspend fun getCharactersByPage(page: Int) : CharacterOne

    suspend fun getCharacterById(id : Int) : CharacterResult

}