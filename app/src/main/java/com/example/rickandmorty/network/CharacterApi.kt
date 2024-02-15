package com.example.rickandmorty.network

import com.example.rickandmorty.character.CharacterOne
import com.example.rickandmorty.character.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int
    )
    : Response<CharacterOne>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterResult>

}