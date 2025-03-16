package com.example.food2forkellm.domain.usecase

import com.example.food2forkellm.data.repository.RecipeRepository
import com.example.food2forkellm.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchRecipesUseCase(private val repository: RecipeRepository) {
    operator fun invoke(query: String): Flow<List<Recipe>> {
        return repository.searchRecipes(query).map { response ->
            response.recipes
                .filter { true } // Filter out recipes with null IDs
                .map { dto ->
                    Recipe(
                        id = dto.recipeId.toString(), // Safe to use !! after filter
                        title = dto.title,
                        publisher = dto.publisher,
                        imageUrl = dto.imageUrl
                    )
                }
        }
    }
}