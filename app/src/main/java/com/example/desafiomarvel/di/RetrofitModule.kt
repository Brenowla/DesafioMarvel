package com.example.desafiomarvel.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

    // Initializing
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

//    @Singleton
//    @Provides
//    fun provideAuthenticationService(retrofit: Retrofit): AutenticateService {
//        return retrofit.create(AutenticateService::class.java)
//    }

//    @Singleton
//    @Provides
//    fun provideChargeService(retrofit: Retrofit): ChargeService {
//        return retrofit.create(ChargeService::class.java)
//    }
}