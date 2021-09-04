package com.example.desafiomarvel.domain.usecase

import com.example.desafiomarvel.base.BaseUseCase
import com.example.desafiomarvel.domain.model.PageInfo
import com.example.desafiomarvel.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetListCharactersUseCase @Inject constructor(
    private val mMarvelRepository: MarvelRepository
): BaseUseCase<PageInfo,GetListCharactersUseCase.Params>() {

    data class Params(
        val offset: Int,
        val limit: Int,
        val query: String?
    )

    override fun doWork(params: Params): Flow<PageInfo?> = flow {
        emit(mMarvelRepository.getListCharacters(
            params.offset,
            params.limit,
            params.query
        ))
    }
}