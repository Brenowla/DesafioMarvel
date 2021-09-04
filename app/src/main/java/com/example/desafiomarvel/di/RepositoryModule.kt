package com.example.desafiomarvel.di

import com.example.desafiomarvel.data.remote.repository.MarvelRepositoryImpl
import com.example.desafiomarvel.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideMarvelRepository(marvelRepository: MarvelRepositoryImpl): MarvelRepository = marvelRepository
}