package com.example.rickandmorty.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.rickandmorty.domain.entity.CharacterResultEntity

class MyDiffUtil(
    private val oldList: List<CharacterResultEntity>,
    private val newList: List<CharacterResultEntity>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].created != newList[newItemPosition].created -> {
                false
            }
            oldList[oldItemPosition].gender != newList[newItemPosition].gender -> {
                false
            }
            oldList[oldItemPosition].image != newList[newItemPosition].image -> {
                false
            }
            oldList[oldItemPosition].location != newList[newItemPosition].location -> {
                false
            }
            oldList[oldItemPosition].name != newList[newItemPosition].name -> {
                false
            }
            oldList[oldItemPosition].origin != newList[newItemPosition].origin -> {
                false
            }
            oldList[oldItemPosition].species != newList[newItemPosition].species -> {
                false
            }
            oldList[oldItemPosition].status != newList[newItemPosition].status -> {
                false
            }
            oldList[oldItemPosition].type != newList[newItemPosition].type -> {
                false
            }
            else -> true
        }
    }
}