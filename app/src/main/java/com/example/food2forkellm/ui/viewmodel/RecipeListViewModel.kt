package com.example.food2forkellm.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkellm.data.remote.NetworkModule
import com.example.food2forkellm.data.repository.RecipeRepository
import com.example.food2forkellm.domain.model.Recipe
import com.example.food2forkellm.domain.usecase.SearchRecipesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {
    private val repository = RecipeRepository(NetworkModule.recipeApi)
    private val searchRecipesUseCase = SearchRecipesUseCase(repository)

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            try {
                Log.d("RecipeListViewModel", "Searching for: $query")
                searchRecipesUseCase(query).collect { recipes ->
                    Log.d("RecipeListViewModel", "Found ${recipes.size} recipes")
                    _recipes.value = recipes
                }
            } catch (e: Exception) {
                Log.e("RecipeListViewModel", "Error searching recipes: ${e.message}")
                _recipes.value = emptyList()
            }
        }
    }
}