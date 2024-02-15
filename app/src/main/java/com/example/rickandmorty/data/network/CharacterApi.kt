package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.network.models.CharacterOne
import com.example.rickandmorty.data.network.models.CharacterResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    suspend fun getCharactersByPage(
        @Query("page") page: Int
    ): Response<CharacterOne>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterResult>

}