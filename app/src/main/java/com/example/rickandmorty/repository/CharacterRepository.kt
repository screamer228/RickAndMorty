package com.example.rickandmorty.repository

import com.example.rickandmorty.character.CharacterOne
import com.example.rickandmorty.character.CharacterResult

interface CharacterRepository {

    suspend fun getCharacters() : CharacterOne

    suspend fun getCharacterById(id : Int) : CharacterResult

}