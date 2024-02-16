package com.example.rickandmorty.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.domain.entity.CharacterResultEntity

class RecyclerAdapter(
    private val fragmentContext: Context,
    private val dataList: List<CharacterResultEntity>,
    private val clickListener: ItemClickListener
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemCharacterBinding, private val context: Context)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: CharacterResultEntity) {
            binding.itemRecyclerTitle.text = item.name
            binding.itemRecyclerImage.load(item.image)
            binding.itemRecyclerDescription.text = item.gender
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, fragmentContext)
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
}
