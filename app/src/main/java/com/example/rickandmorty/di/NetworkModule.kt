package com.example.rickandmorty.di

import com.example.rickandmorty.network.CharacterApi
import com.example.rickandmorty.network.RetrofitClient
import com.example.rickandmorty.repository.CharacterRepository
import com.example.rickandmorty.repository.CharacterRepositoryImpl
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
    fun providesRequestsApi() : CharacterApi{
        return RetrofitClient.getInstance().create(CharacterApi::class.java)
    }

}