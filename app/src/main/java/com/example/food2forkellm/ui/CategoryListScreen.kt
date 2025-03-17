package com.example.food2forkellm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryListScreen(categories: List<String>, onCategoryClick: (String) -> Unit) {
    LazyColumn {
        items(categories) { category ->
            CategoryItem(category = category, onCategoryClick = onCategoryClick)
        }
    }
}

@Composable
fun CategoryItem(category: String, onCategoryClick: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = category,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onCategoryClick(category)},
            fontSize = 16.sp
        )
    }
}