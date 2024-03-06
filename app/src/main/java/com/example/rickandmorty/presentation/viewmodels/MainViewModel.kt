package com.example.rickandmorty.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersByPageUseCase: GetCharactersByPageUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel() {

    var currentPage: Int = 1

    private val navigateToDetail = MutableLiveData<Int?>()
    val navigateToDetailResult: MutableLiveData<Int?>
        get() = navigateToDetail

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

    fun navigateToDetail(itemId: Int) {
        navigateToDetail.postValue(itemId)
    }
    fun resetNavigation() {
        navigateToDetail.postValue(null)
    }

    fun loadNextPage() {
        getCharactersByPage(currentPage)
    }
    fun loadPrevPage() {
        getCharactersByPage(currentPage)
    }
}