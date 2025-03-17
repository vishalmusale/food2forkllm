package com.example.food2forkellm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.food2forkellm.data.model.RecipeDetails

@Composable
fun RecipeDetailsScreen(recipeDetails: RecipeDetails, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = recipeDetails.title,
                fontSize = 24.sp,
                color = androidx.compose.ui.graphics.Color.Red,
            )

            Spacer(modifier = Modifier.padding(10.dp))

            AsyncImage(
                model = recipeDetails.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Text(text = "Ingredients:", fontSize = 20.sp, modifier = Modifier.padding(vertical = 8.dp))
            recipeDetails.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient", fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
            }
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Back", fontSize = 22.sp, modifier = Modifier.padding(4.dp))
            }
        }
    }
}