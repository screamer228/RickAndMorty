package com.example.rickandmorty.di

import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.data.network.RetrofitClient
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun providesCharacterRepository(characterApi: CharacterApi) : CharacterRepository {
        return CharacterRepositoryImpl(characterApi)
    }

    @Provides
    @Singleton
    fun providesRequestsApi() : CharacterApi {
        return RetrofitClient.getInstance().create(CharacterApi::class.java)
    }

}