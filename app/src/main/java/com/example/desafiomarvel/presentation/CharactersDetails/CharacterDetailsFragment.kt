package com.example.desafiomarvel.presentation.CharactersDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.desafiomarvel.databinding.FragmentCharacterDetailsBinding
import com.example.desafiomarvel.extensions.load

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private val mAdapter: CharacterDetailsAdapter = CharacterDetailsAdapter()
    private val mArgs by navArgs<CharacterDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvComics.adapter = mAdapter
        mAdapter.submitList(mArgs.character.comics)
        configLayout()
    }

    private fun configLayout() {
        binding.ivIcon.load(mArgs.character.img)
        binding.character = mArgs.character
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}