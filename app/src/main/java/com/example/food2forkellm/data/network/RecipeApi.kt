package com.example.food2forkellm.data.network

import com.example.food2forkellm.data.model.RecipeDetailsResponse
import com.example.food2forkellm.data.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/search")
    suspend fun searchRecipes(@Query("q") query: String): RecipeResponse

    @GET("api/get")
    suspend fun getRecipeDetails(@Query("rId") recipeId: String): RecipeDetailsResponse
}