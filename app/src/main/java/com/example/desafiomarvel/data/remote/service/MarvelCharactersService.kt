package com.example.desafiomarvel.data.remote.service

import com.example.desafiomarvel.data.remote.model.ListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelCharactersService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") name: String?
    ): ListResponse
}