package com.example.food2forkellm.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import com.example.food2forkellm.data.network.RetrofitInstance
import com.example.food2forkellm.data.repository.RecipeRepository
import com.example.food2forkellm.ui.viewmodel.RecipeViewModel
import com.example.food2forkellm.ui.viewmodel.RecipeViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RecipeViewModel

    @SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = RecipeRepository(RetrofitInstance.api)
        viewModel = ViewModelProvider(this, RecipeViewModelFactory(repository))[RecipeViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            val showDetails by viewModel.showDetails.collectAsState()
            val recipeDetails by viewModel.recipeDetails.collectAsState()

            if (showDetails) {
                if (recipeDetails != null) {
                    RecipeDetailsScreen(
                        recipeDetails = recipeDetails!!,
                        onBackClick = { viewModel.resetShowDetails() }
                    )
                } else {
                    Text(text = "Loading recipe details...")
                }
            } else {
                RecipeListScreen(
                    viewModel = viewModel,
                    onRecipeClick = { recipeId ->
                        viewModel.getRecipeDetails(recipeId)
                    }
                )
            }
        }
    }
}