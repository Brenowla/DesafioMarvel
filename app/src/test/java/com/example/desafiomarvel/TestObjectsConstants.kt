package com.example.desafiomarvel

import com.example.desafiomarvel.data.remote.model.*
import com.example.desafiomarvel.domain.model.Character
import com.example.desafiomarvel.domain.model.Comics
import com.example.desafiomarvel.domain.model.PageInfo

object TestObjectsConstants {

    val itemResponse = ItemsResponse(
        "URI",
        "Comic 1"
    )

    val comic = Comics(
        "URI",
        "Comic 1"
    )

    val characterResponse = CharacterResponse(
        1,
        "name",
        "description",
        ThumbnailResponse(
            "path",
            "extension"
        ),
        ComicsResponse(
            "URI",
            listOf(itemResponse)
        )
    )

    val character = Character(
        1,
        "name",
        "description",
        "path/standard_medium.extension",
        listOf(comic)
    )

    const val offset = 0
    const val limit = 4
    const val query = ""

    val listResponse = ListResponse(
        123,
        "etag",
        DataResponse(
            0,
            4,
            12,
            8,
            listOf(characterResponse)
        )
    )

    val pageInfo = PageInfo(
        0,
        4,
        12,
        8,
        listOf(character)
    )

    val runtimeException = RuntimeException()
}