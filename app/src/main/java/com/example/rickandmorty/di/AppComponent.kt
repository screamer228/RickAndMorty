package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.CharacterFragment
import com.example.rickandmorty.presentation.DetailFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectCharacter(characterFragment: CharacterFragment)

    fun injectDetail(detailFragment: DetailFragment)

}