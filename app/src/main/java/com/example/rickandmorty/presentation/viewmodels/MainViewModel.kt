package com.example.rickandmorty.presentation.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.domain.entity.CharacterResultEntity
import com.example.rickandmorty.domain.entity.CharactersEntity
import com.example.rickandmorty.domain.usecase.getCharachersByPage.GetCharactersByPageUseCase
import com.example.rickandmorty.domain.usecase.getCharacterById.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


private const val MIN_PAGE: Int = 1
private const val MAX_PAGE: Int = 42

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharactersByPageUseCase: GetCharactersByPageUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : ViewModel() {

    var currentPage: Int = 1

    private val _navigateToDetail = MutableLiveData<Int?>()
    val navigateToDetail: MutableLiveData<Int?>
        get() = _navigateToDetail

    private val _characters: MutableLiveData<CharactersEntity> = MutableLiveData()
    val characters: LiveData<CharactersEntity> = _characters

    private val _characterDetail: MutableLiveData<CharacterResultEntity> = MutableLiveData()
    val characterDetail: LiveData<CharacterResultEntity> = _characterDetail

    private val _isNeedShowToast: MutableLiveData<CharacterResultEntity> = MutableLiveData()

    fun getCharactersByPage(page: Int) {
        val result = getCharactersByPageUseCase.getCharactersByPage(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _characters.value = it
            }, {
                Log.e(TAG, "it ${it.localizedMessage}")
        })
    }

    fun getCharacterById(id: Int) {
        val result = getCharacterByIdUseCase.getCharacterById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _characterDetail.value = it
            }, {
                Log.e(TAG, "it ${it.localizedMessage}")
            })
    }

    fun navigateToDetail(itemId: Int) {
        _navigateToDetail.postValue(itemId)
    }

    fun resetNavigation() {
        _navigateToDetail.postValue(null)
    }

    fun loadNextPage() {
        if (currentPage < MAX_PAGE) {
            currentPage++
            getCharactersByPage(currentPage)
        }
        //TODO else Toast
    }

    fun loadPrevPage() {
        if (currentPage > MIN_PAGE) {
            currentPage--
            getCharactersByPage(currentPage)
        }
        //TODO else Toast
    }
}