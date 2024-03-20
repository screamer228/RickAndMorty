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
import com.example.rickandmorty.presentation.viewmodels.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideMainViewModelFactory(
        getCharactersByPageUseCase: GetCharactersByPageUseCase,
        getCharacterByIdUseCase: GetCharacterByIdUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getCharactersByPageUseCase,
            getCharacterByIdUseCase
        )
    }

    @Provides
    fun provideGetCharactersByPageUseCase(
        characterRepository: CharacterRepository
    ): GetCharactersByPageUseCase {
        return GetCharactersByPageUseCaseImpl(characterRepository)
    }

    @Provides
    fun provideGetCharacterByIdUseCase(
        characterRepository: CharacterRepository
    ): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCaseImpl(characterRepository)
    }

    @Provides
    fun provideCharacterRepository(
        characterApi: CharacterApi
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            characterApi,
            characterResultMapper = CharacterResultMapper(),
            charactersResponseMapper = CharactersResponseMapper()
        )
    }

    @Provides
    fun provideRequestsApi(): CharacterApi {
        return RetrofitClient.getInstance().create(CharacterApi::class.java)
    }
}