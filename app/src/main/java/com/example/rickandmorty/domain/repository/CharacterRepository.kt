package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getCharactersByPage(page: Int): Single<CharactersEntity>

    fun getCharacterById(id: Int): Single<CharacterResultEntity>

}