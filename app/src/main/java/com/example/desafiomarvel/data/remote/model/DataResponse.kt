package com.example.desafiomarvel.data.remote.model

data class DataResponse(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterResponse>
)