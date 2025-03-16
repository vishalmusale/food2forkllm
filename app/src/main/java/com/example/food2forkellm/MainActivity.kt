package com.example.food2forkellm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.food2forkellm.ui.navigation.NavGraph
import com.example.food2forkellm.ui.theme.Food2ForkeLLMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Food2ForkeLLMTheme {
                NavGraph()
            }
        }
    }
}