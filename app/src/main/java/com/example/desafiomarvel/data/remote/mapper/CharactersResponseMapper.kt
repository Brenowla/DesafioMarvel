package com.example.desafiomarvel.data.remote.mapper

import com.example.desafiomarvel.base.BaseMapper
import com.example.desafiomarvel.data.remote.model.CharacterResponse
import com.example.desafiomarvel.domain.model.Character

object CharactersResponseMapper: BaseMapper<CharacterResponse, Character>() {
    override fun transformTo(source: CharacterResponse): Character {
        return Character(
            source.id,
            source.name,
            source.description,
            "${source.thumbnail.path}/standard_medium.${source.thumbnail.extension}",
            ItemsMapper.transformToList(source.comics.items)
        )
    }
}