package com.example.food2forkellm.domain.usecase

import com.example.food2forkellm.data.repository.RecipeRepository
import com.example.food2forkellm.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipeByIdUseCase(private val repository: RecipeRepository) {
    operator fun invoke(recipeId: String): Flow<Recipe> {
        return repository.getRecipe(recipeId).map { dto ->
            if (dto.recipeId == null) {
                throw IllegalStateException("Recipe ID cannot be null")
            }
            Recipe(
                id = dto.recipeId, // Safe since we checked
                title = dto.title,
                publisher = dto.publisher,
                imageUrl = dto.imageUrl,
                ingredients = dto.ingredients ?: emptyList()
            )
        }
    }
}