package com.example.food2forkellm.domain.model

data class Recipe(
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val ingredients: List<String> = emptyList()
)
