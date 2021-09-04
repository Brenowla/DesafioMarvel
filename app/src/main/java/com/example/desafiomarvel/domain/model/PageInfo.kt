package com.example.desafiomarvel.domain.model

data class PageInfo(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val characters: List<Character>
)
