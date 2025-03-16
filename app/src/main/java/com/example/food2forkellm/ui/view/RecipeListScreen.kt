package com.example.food2forkellm.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.food2forkellm.ui.view.components.RecipeCard
import com.example.food2forkellm.ui.view.components.SearchBar
import com.example.food2forkellm.ui.viewmodel.RecipeListViewModel

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val recipes = viewModel.recipes.collectAsState().value

    Scaffold(
        topBar = {
            SearchBar(
                onSearch = { query ->
                    viewModel.searchRecipes(query)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier,
            contentPadding = padding
        ) {
            items(recipes) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    onClick = { onRecipeClick(recipe.id) }
                )
            }
        }
    }
}