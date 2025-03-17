package com.example.food2forkellm.data.repository

import android.util.Log
import com.example.food2forkellm.data.model.Recipe
import com.example.food2forkellm.data.model.RecipeDetails
import com.example.food2forkellm.data.network.RecipeApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepository(private val api: RecipeApi) {

    fun searchRecipes(query: String): Flow<List<Recipe>> = flow {
        try {
            val response = api.searchRecipes(query)
            Log.d("API_RESPONSE", "Search Recipes Response: ${response.recipes}")
            emit(response.recipes)
        } catch (e: Exception) {
            Log.e("API_RESPONSE", "Error fetching recipes", e)
            emit(emptyList())
        }
    }

    fun getRecipeDetails(recipeId: String): Flow<RecipeDetails> = flow {
        try {
            val response = api.getRecipeDetails(recipeId)
            Log.d("API_CALL", "Recipe Details Fetched: ${response.recipe}")
            emit(response.recipe)
        } catch (e: Exception) {
            Log.e("API_CALL", "Error fetching recipe details", e)
            emit(RecipeDetails("", emptyList(), "", "", ""))
        }
    }
}