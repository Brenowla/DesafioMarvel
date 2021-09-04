package com.example.desafiomarvel.data.remote.repository

import com.example.desafiomarvel.data.remote.mapper.ListResponseMapper
import com.example.desafiomarvel.data.remote.service.MarvelCharactersService
import com.example.desafiomarvel.domain.model.PageInfo
import com.example.desafiomarvel.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val mMarvelCharactersService: MarvelCharactersService
): MarvelRepository {

    override suspend fun getListCharacters(offset: Int, limit: Int, query: String?): PageInfo =
        ListResponseMapper.transformTo(
            mMarvelCharactersService.getCharacters(
                offset,
                limit,
                query
            )
        )

}