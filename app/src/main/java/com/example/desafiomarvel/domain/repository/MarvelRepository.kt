package com.example.desafiomarvel.domain.repository

import com.example.desafiomarvel.domain.model.PageInfo

interface MarvelRepository {
    suspend fun getListCharacters(offset: Int, limit: Int, query: String?): PageInfo
}