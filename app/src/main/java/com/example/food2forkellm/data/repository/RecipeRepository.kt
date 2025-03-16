package com.example.food2forkellm.data.repository

import android.util.Log
import com.example.food2forkellm.data.remote.RecipeApi
import com.example.food2forkellm.data.remote.dto.RecipeDto
import com.example.food2forkellm.data.remote.dto.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RecipeRepository(private val api: RecipeApi) {
    fun searchRecipes(query: String): Flow<SearchResponse> = flow {
        try {
            if (query.isBlank()) throw IllegalArgumentException("Query cannot be empty")
            val response = api.searchRecipes(query)
            Log.d("RecipeRepository", "Search response: $response")
            emit(response)
        } catch (e: HttpException) {
            Log.e("RecipeRepository", "Error: ${e.message}")
            throw e
        }
    }

    fun getRecipe(recipeId: String): Flow<RecipeDto> = flow {
        try {
            if (recipeId.isBlank()) throw IllegalArgumentException("Recipe ID cannot be empty")
            val response = api.getRecipe(recipeId)
            emit(response.recipe)
        } catch (e: HttpException) {
            // Log or handle error
            throw e
        }
    }
}