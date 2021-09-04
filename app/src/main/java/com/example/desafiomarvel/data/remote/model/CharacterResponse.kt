package com.example.desafiomarvel.data.remote.model

data class CharacterResponse(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: ThumbnailResponse,
    val comics: ComicsResponse
)