package com.example.desafiomarvel.presentation.CharactersDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomarvel.databinding.ItemComicsRecyclerBinding
import com.example.desafiomarvel.domain.model.Comics

class CharacterDetailsAdapter : ListAdapter<Comics, CharacterDetailsAdapter.CharactersDetailsViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Comics>() {
        override fun areItemsTheSame(oldItem: Comics, newItem: Comics): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Comics, newItem: Comics): Boolean {
            return oldItem == newItem
        }
    }

    class CharactersDetailsViewHolder(val binding: ItemComicsRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

            companion object {
                fun from(parent: ViewGroup): CharactersDetailsViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = ItemComicsRecyclerBinding.inflate(layoutInflater, parent, false)
                    return CharactersDetailsViewHolder(binding)
                }
            }

            fun bind(item: Comics) {
                binding.tvName.text = item.name
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDetailsViewHolder {
        return CharactersDetailsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharactersDetailsViewHolder, position: Int) {
        getItem(position)?.let { comics ->
            holder.bind(comics)
        }
    }
}