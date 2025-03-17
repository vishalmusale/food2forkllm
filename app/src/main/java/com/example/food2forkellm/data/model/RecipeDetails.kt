package com.example.food2forkellm.data.model

import com.google.gson.annotations.SerializedName

data class RecipeDetails(
    @SerializedName("publisher") val publisher: String,
    @SerializedName("ingredients") val ingredients: List<String>,
    @SerializedName("source_url") val sourceUrl: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("title") val title: String
)

data class RecipeDetailsResponse(
    @SerializedName("recipe") val recipe: RecipeDetails
)

data class RecipeResponse(
    @SerializedName("recipes") val recipes: List<Recipe>
)