package com.example.rickandmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun getCharactersByPage(page: Int){
        viewModelScope.launch(Dispatchers.IO){
            val charactersResult = getCharactersByPageUseCase.getCharactersByPage(page)
            characters.postValue(charactersResult)
        }
    }

    fun getCharacterById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val characterDetailResult = getCharacterByIdUseCase.getCharacterById(id)
            characterDetail.postValue(characterDetailResult)
        }
    }
}