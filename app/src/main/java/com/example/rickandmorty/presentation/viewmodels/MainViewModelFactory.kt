package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase

class MainViewModelFactory(
    val getCharactersByPageUseCase: GetCharactersByPageUseCase,
    val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getCharactersByPageUseCase,
            getCharacterByIdUseCase
        ) as T
    }
}