package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp2_8inf865.ui.viewmodels.JokeViewModel

@Composable
fun StoryElementsScreen(StartValue: Int = 90, viewModel: JokeViewModel ){

//    Text(text = viewModel.getJokes(),
//        Modifier.padding(start = StartValue.dp))

    //var newJoke = viewModel.getNewJoke()
    var newJoke = "TODO"
    Column {
        Text(text = "New Joke : $newJoke",
            Modifier.padding(start = StartValue.dp))

        Text(text = "TODO Change Text without Async",
            Modifier.padding(start = StartValue.dp))
    }

}