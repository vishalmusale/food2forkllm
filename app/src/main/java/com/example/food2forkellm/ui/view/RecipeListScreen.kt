package com.example.food2forkellm.ui.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.food2forkellm.ui.view.components.RecipeCard
import com.example.food2forkellm.ui.view.components.SearchBar
import com.example.food2forkellm.ui.viewmodel.RecipeListViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    onRecipeClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val recipes = viewModel.recipes.collectAsState().value
    var query by remember { mutableStateOf("") } // Local state for search query

    Scaffold(
        topBar = {
            SearchBar(
                query = query,
                onQueryChange = { newQuery ->
                    query = newQuery
                },
                onSearch = {
                    val trimmedQuery = query.trim()
                    if (trimmedQuery.isNotEmpty()) {
                        viewModel.searchRecipes(trimmedQuery) // Use text as query on click
                    }
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