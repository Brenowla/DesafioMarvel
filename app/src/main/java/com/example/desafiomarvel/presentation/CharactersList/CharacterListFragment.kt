package com.example.desafiomarvel.presentation.CharactersList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.desafiomarvel.DesafioMarvelApp
import com.example.desafiomarvel.databinding.FragmentCharactersListBinding
import com.example.desafiomarvel.extensions.textChanges
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mCharacterListViewModel by viewModels<CharacterListViewModel> { viewModelFactory }

    private val mAdapter: CharactersListAdapter = CharactersListAdapter()

    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DesafioMarvelApp.appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
//        CharacterListFragmentDirections.actionCharactersListToCharacterDetail().run {
//            findNavController().navigate(this)
//        }
        mCharacterListViewModel.getCharacters()
        binding.rvListCharacters.adapter = mAdapter
        observers()
        listeners()
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun listeners() {
        binding.ivBack.setOnClickListener {
            mCharacterListViewModel.backPage()
        }
        binding.ivNext.setOnClickListener {
            mCharacterListViewModel.nextPage()
        }
        lifecycleScope.launch {
            binding.etName.textChanges()
                .debounce(500L)
                .collect { query ->
                    mCharacterListViewModel.loadInitialPage(query?.toString() ?: "")
                }
        }
    }

    private fun observers() {
        mCharacterListViewModel.page.observe(viewLifecycleOwner) { page ->
            mAdapter.submitList(page.characters)
        }
        mCharacterListViewModel.loading.observe(viewLifecycleOwner) { loading->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}