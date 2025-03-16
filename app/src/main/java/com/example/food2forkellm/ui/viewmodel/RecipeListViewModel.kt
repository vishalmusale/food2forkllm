package com.example.food2forkellm.ui.viewmodel

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
            searchRecipesUseCase(query).collect { recipes ->
                _recipes.value = recipes
            }
        }
    }
}