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
            getRecipeByIdUseCase(recipeId).collect { recipe ->
                _recipe.value = recipe
            }
        }
    }
}