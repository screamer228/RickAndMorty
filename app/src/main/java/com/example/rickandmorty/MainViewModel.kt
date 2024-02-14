package com.example.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.character.CharacterOne
import com.example.rickandmorty.character.CharacterResult
import com.example.rickandmorty.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val characterRepository: CharacterRepository
): ViewModel() {

    private val characters: MutableLiveData<CharacterOne> = MutableLiveData()
    val charactersResult: LiveData<CharacterOne> = characters

    private val characterDetail: MutableLiveData<CharacterResult> = MutableLiveData()
    val characterDetailResult: LiveData<CharacterResult> = characterDetail

    suspend fun getCharacters(callback: () -> Unit){
        withContext(Dispatchers.IO){
            val charactersResult = characterRepository.getCharacters()
            characters.postValue(charactersResult)
        }
        callback()
    }

    suspend fun getCharacterById(id: Int){
        withContext(Dispatchers.IO){
            val characterDetailResult = characterRepository.getCharacterById(id)
            characterDetail.postValue(characterDetailResult)
        }
    }
}