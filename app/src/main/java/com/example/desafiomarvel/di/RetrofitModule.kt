package com.example.desafiomarvel.di

import com.example.desafiomarvel.BuildConfig
import com.example.desafiomarvel.data.remote.service.MarvelCharactersService
import com.example.desafiomarvel.extensions.md5
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class RetrofitModule {

    private val mCredentialsInterceptor = Interceptor {chain ->
        val original = chain.request()
        val originalHttpUrl = original.url

        val ts = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", (ts+BuildConfig.SECRET_KEY+BuildConfig.API_KEY).md5())
            .build()
        chain.proceed(original.newBuilder().url(url).build())
    }

    // Initializing
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(mCredentialsInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelCharactersService(retrofit: Retrofit): MarvelCharactersService {
        return retrofit.create(MarvelCharactersService::class.java)
    }
}