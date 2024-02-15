package com.example.rickandmorty

import com.example.rickandmorty.domain.models.character.CharacterResult

interface ItemClickListener {
    fun onItemClick(data: Int)
}