package com.example.food2forkellm.data.remote.dto

data class SearchResponse(
    val count: Int,
    val recipes: List<RecipeDto>
)

data class RecipeDto(
    val recipeId: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val ingredients: List<String>? = null
)