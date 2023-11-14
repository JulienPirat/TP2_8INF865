package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(StartValue: Int = 90) {
    Text(text = "Here will be everything that is related to sessions that are currently being played.",
    Modifier.padding(start = StartValue.dp))
}