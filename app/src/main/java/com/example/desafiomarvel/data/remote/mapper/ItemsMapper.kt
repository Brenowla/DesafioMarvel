package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.base.BaseMapper
import com.example.desafiomarvel.data.remote.model.ItemsResponse
import com.example.desafiomarvel.domain.model.Comics

object ItemsMapper: BaseMapper<ItemsResponse, Comics>() {
    override fun transformTo(source: ItemsResponse): Comics {
        return Comics(
            source.resourceURI,
            source.name
        )
    }
}