package com.example.food2forkellm.data.remote

import com.example.food2forkellm.data.remote.dto.RecipeResponse
import com.example.food2forkellm.data.remote.dto.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("api/search")
    suspend fun searchRecipes(@Query("q") query: String): SearchResponse

    @GET("api/get")
    suspend fun getRecipe(@Query("rId") recipeId: String): RecipeResponse
}