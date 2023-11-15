package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StoryElementsScreen(StartValue: Int = 90) {
    Text(text = "Here will be everything that is story related, such as players, characters, playbooks and so on.",
        Modifier.padding(start = StartValue.dp))
}