package com.example.rickandmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersByPageUseCase: GetCharactersByPageUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
): ViewModel() {

    private val characters: MutableLiveData<CharactersEntity> = MutableLiveData()
    val charactersResult: LiveData<CharactersEntity> = characters

    private val characterDetail: MutableLiveData<CharacterResultEntity> = MutableLiveData()
    val characterDetailResult: LiveData<CharacterResultEntity> = characterDetail

    suspend fun getCharactersByPage(page: Int){
        withContext(Dispatchers.IO){
            val charactersResult = getCharactersByPageUseCase.getCharactersByPage(page)
            characters.postValue(charactersResult)
        }
    }

    suspend fun getCharacterById(id: Int){
        withContext(Dispatchers.IO){
            val characterDetailResult = getCharacterByIdUseCase.getCharacterById(id)
            characterDetail.postValue(characterDetailResult)
        }
    }
}