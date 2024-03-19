package com.example.rickandmorty.data.network

import com.example.rickandmorty.data.network.models.CharactersResponseDTO
import com.example.rickandmorty.data.network.models.CharacterResultDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {

    @GET("character/")
    fun getCharactersByPage(
        @Query("page") page: Int
    ): Single<Response<CharactersResponseDTO>>

    @GET("character/{id}")
    fun getCharacterById(
        @Path("id") id: Int
    ): Single<Response<CharacterResultDTO>>

}