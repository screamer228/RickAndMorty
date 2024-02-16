package com.example.rickandmorty.di

import com.example.rickandmorty.data.mappers.CharacterResultMapper
import com.example.rickandmorty.data.mappers.CharactersResponseMapper
import com.example.rickandmorty.data.network.CharacterApi
import com.example.rickandmorty.data.network.RetrofitClient
import com.example.rickandmorty.domain.repository.CharacterRepository
import com.example.rickandmorty.data.repository.CharacterRepositoryImpl
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCaseImpl
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCaseImpl
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
    fun providesGetCharactersByPageUseCase(characterRepository: CharacterRepository
    ) : GetCharactersByPageUseCase {
        return GetCharactersByPageUseCaseImpl(characterRepository)
    }

    @Provides
    @Singleton
    fun providesGetCharacterByIdUseCase(characterRepository: CharacterRepository
    ) : GetCharacterByIdUseCase {
        return GetCharacterByIdUseCaseImpl(characterRepository)
    }

    @Provides
    @Singleton
    fun providesCharacterRepository(characterApi: CharacterApi
    ) : CharacterRepository {
        return CharacterRepositoryImpl(characterApi,
            characterResultMapper = CharacterResultMapper(),
            charactersResponseMapper = CharactersResponseMapper(
            )
        )
    }

    @Provides
    @Singleton
    fun providesRequestsApi() : CharacterApi {
        return RetrofitClient.getInstance().create(CharacterApi::class.java)
    }
}