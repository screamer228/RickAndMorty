package com.example.rickandmorty.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.entity.CharacterResultEntity

class RecyclerAdapter(
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataList: MutableList<CharacterResultEntity> = mutableListOf()

    class ViewHolder(
        private val binding: ItemCharacterBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterResultEntity) {
            binding.itemRecyclerTitle.text = item.name
            binding.itemRecyclerImage.load(item.image)
            binding.itemRecyclerDescription.text = item.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item.id)
        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateList(charactersList: List<CharacterResultEntity>){
        dataList = charactersList.toMutableList()
        notifyDataSetChanged()
    }
}
