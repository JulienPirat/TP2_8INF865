package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(StartValue: Int = 90) {
    Text(text = "Home, there will be a summary of the current game, and a list of the last played games.",
        Modifier.padding(start = StartValue.dp))
}