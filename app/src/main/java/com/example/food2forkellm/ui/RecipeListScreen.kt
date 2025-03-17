package com.example.food2forkellm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.food2forkellm.data.model.Recipe
import com.example.food2forkellm.ui.viewmodel.RecipeViewModel

@Composable
fun RecipeListScreen(
    viewModel: RecipeViewModel,
    onRecipeClick: (String) -> Unit
) {
    Column {
        // Add the SearchBar at the top
        SearchBar { query ->
            viewModel.searchRecipes(query)
        }

        val recipes by viewModel.recipes.collectAsState()

        if (recipes.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(recipes) { recipe ->
                    RecipeItem(recipe = recipe, onClick = { onRecipeClick(recipe.recipeId) })
                }
            }
        }

        val errorMessage by viewModel.errorMessage.collectAsState()
        if (errorMessage != null) {
            Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onClick() } // Make the entire item clickable
    ) {
        Row {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(width = 100.dp, height = 100.dp)
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = recipe.title,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}