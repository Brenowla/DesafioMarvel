package com.example.desafiomarvel.domain.model

data class Character (
    val id: Int,
    val name: String,
    val description: String,
    val img: String,
    val comics: List<Comics>
)