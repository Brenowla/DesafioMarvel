package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.base.BaseMapper
import com.example.desafiomarvel.data.remote.model.ListResponse
import com.example.desafiomarvel.domain.model.PageInfo

object ListResponseMapper: BaseMapper<ListResponse, PageInfo>() {
    override fun transformTo(source: ListResponse): PageInfo {
        return PageInfo(
            source.data.offset,
            source.data.limit,
            source.data.total,
            source.data.count,
            CharactersResponseMapper.transformToList(source.data.results)
        )
    }
}