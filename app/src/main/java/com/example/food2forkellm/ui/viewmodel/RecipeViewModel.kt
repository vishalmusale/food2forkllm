package com.example.food2forkellm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkellm.data.model.Recipe
import com.example.food2forkellm.data.model.RecipeDetails
import com.example.food2forkellm.data.repository.RecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    private val _recipeDetails = MutableStateFlow<RecipeDetails?>(null)
    val recipeDetails: StateFlow<RecipeDetails?> get() = _recipeDetails

    private val _showDetails = MutableStateFlow(false)
    val showDetails: StateFlow<Boolean> get() = _showDetails

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            try {
                _errorMessage.value = null
                repository.searchRecipes(query).collect { recipeList ->
                    _recipes.value = recipeList
                }

            } catch (e: Exception) {
                _errorMessage.value = "Failed to load recipes. Please try again."
            }
        }
    }

    fun getRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            repository.getRecipeDetails(recipeId).collect { details ->
                _recipeDetails.value = details
                _showDetails.value = true // Show details screen
            }
        }
    }

    fun resetShowDetails() {
        _showDetails.value = false // Reset to hide details screen
    }
}