package com.example.food2forkellm.data.model

import com.google.gson.annotations.SerializedName

data class Recipe(
    @SerializedName("recipe_id") val recipeId: String,
    @SerializedName("title") val title: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("publisher") val publisher: String
)