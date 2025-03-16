package com.example.food2forkellm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food2forkellm.data.remote.NetworkModule
import com.example.food2forkellm.data.repository.RecipeRepository
import com.example.food2forkellm.domain.model.Recipe
import com.example.food2forkellm.domain.usecase.GetRecipeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    private val repository = RecipeRepository(NetworkModule.recipeApi)
    private val getRecipeByIdUseCase = GetRecipeByIdUseCase(repository)

    private val _recipe = MutableStateFlow<Recipe?>(null)
    val recipe: StateFlow<Recipe?> = _recipe

    fun getRecipe(recipeId: String) {
        viewModelScope.launch {
            try {
                getRecipeByIdUseCase(recipeId).collect { recipe ->
                    _recipe.value = recipe
                }
            } catch (e: Exception) {
                _recipe.value = null // Handle error by clearing recipe
                // Optionally log the error or notify UI
            }
        }
    }
}