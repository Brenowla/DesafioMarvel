package com.example.desafiomarvel

import android.app.Application
import com.example.desafiomarvel.di.ApplicationComponent
import com.example.desafiomarvel.di.DaggerApplicationComponent

class DesafioMarvelApp : Application() {

    companion object{
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)
    }
}