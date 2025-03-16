package com.example.food2forkellm.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food2forkellm.ui.view.RecipeDetailScreen
import com.example.food2forkellm.ui.view.RecipeListScreen
import com.example.food2forkellm.ui.viewmodel.RecipeDetailViewModel
import com.example.food2forkellm.ui.viewmodel.RecipeListViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipe_list") {
        composable("recipe_list") {
            val viewModel: RecipeListViewModel = viewModel()
            RecipeListScreen(
                viewModel = viewModel,
                onRecipeClick = { recipeId ->
                    navController.navigate("recipe_detail/$recipeId")
                }
            )
        }
        composable("recipe_detail/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            val viewModel: RecipeDetailViewModel = viewModel()
            RecipeDetailScreen(viewModel = viewModel, recipeId = recipeId)
        }
    }
}