package com.example.food2forkellm.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food2forkellm.ui.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    viewModel: RecipeDetailViewModel,
    recipeId: String,
    modifier: Modifier = Modifier
) {
    viewModel.getRecipe(recipeId)
    val recipe = viewModel.recipe.collectAsState().value ?: return

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = recipe.title)
        Text(text = "Publisher: ${recipe.publisher}")
        Text(text = "Ingredients:")
        recipe.ingredients.forEach { ingredient ->
            Text(text = "- $ingredient")
        }
    }
}