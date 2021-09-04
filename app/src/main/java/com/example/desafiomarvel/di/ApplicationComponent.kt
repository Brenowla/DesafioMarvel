package com.example.desafiomarvel.di

import android.content.Context
import com.example.desafiomarvel.presentation.CharactersList.CharacterListFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelBuilderModule::class, RetrofitModule::class, ViewModelModule::class, RepositoryModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    fun inject(fragment: CharacterListFragment)
}
