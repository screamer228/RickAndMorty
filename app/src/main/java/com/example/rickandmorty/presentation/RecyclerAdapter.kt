package com.example.rickandmorty.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.entity.CharacterResultEntity

class RecyclerAdapter(
    private val clickListener: ItemClickListener,
    private val callback: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var dataList: MutableList<CharacterResultEntity> = mutableListOf()

    class ViewHolder(
        private val binding: ItemCharacterBinding,
        private val callback: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterResultEntity) {
            itemView.setOnClickListener {
                callback.invoke(item.id)
            }
            binding.itemRecyclerTitle.text = item.name
            binding.itemRecyclerImage.load(item.image)
            binding.itemRecyclerDescription.text = item.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding) {itemId ->
            callback.invoke(itemId)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            clickListener.onItemClick(item.id)
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
