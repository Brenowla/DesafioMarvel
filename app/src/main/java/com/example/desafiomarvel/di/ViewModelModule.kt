package com.example.desafiomarvel.di

import androidx.lifecycle.ViewModel
import com.example.desafiomarvel.presentation.CharactersList.CharacterListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap()
    @ViewModelKey(CharacterListViewModel::class)
    fun bindCharacterListViewModel(viewModel: CharacterListViewModel) : ViewModel
}