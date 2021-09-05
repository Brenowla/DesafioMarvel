package com.example.desafiomarvel.presentation.CharactersList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomarvel.databinding.ItemCharacterRecyclerBinding
import com.example.desafiomarvel.domain.model.Character
import com.example.desafiomarvel.extensions.load

class CharactersListAdapter(
    private val mClick: (character: Character) -> Unit
) : ListAdapter<Character, CharactersListAdapter.CharactersListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    class CharactersListViewHolder(val binding: ItemCharacterRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CharactersListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCharacterRecyclerBinding.inflate(layoutInflater, parent, false)
                return CharactersListViewHolder(binding)
            }
        }

        fun bind(character: Character, onClick: (character: Character) -> Unit) {
            binding.tvName.text = character.name
            binding.ivIcon.load(character.img)
            binding.clItem.setOnClickListener {
                onClick(character)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder =
        CharactersListViewHolder.from(parent)


    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        getItem(position)?.let { character ->
            holder.bind(character) { charSelected ->
                mClick(charSelected)
            }
        }
    }
}