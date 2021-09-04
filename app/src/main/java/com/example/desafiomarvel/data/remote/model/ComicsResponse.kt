package com.example.desafiomarvel.data.remote.model

data class ComicsResponse (
    val collectionURI: String,
    val items: List<ItemsResponse>
)