package com.example.food2forkellm.data.repository

import com.example.food2forkellm.data.remote.RecipeApi
import com.example.food2forkellm.data.remote.dto.RecipeDto
import com.example.food2forkellm.data.remote.dto.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepository(private val api: RecipeApi) {
    fun searchRecipes(query: String): Flow<SearchResponse> = flow {
        emit(api.searchRecipes(query))
    }

    fun getRecipe(recipeId: String): Flow<RecipeDto> = flow {
        emit(api.getRecipe(recipeId))
    }
}