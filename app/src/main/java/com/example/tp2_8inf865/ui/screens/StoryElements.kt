package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.ui.viewmodels.JokeViewModel

@Composable
fun StoryElementsScreen(modifier : Modifier, StartValue: Int = 90, viewModel: JokeViewModel ){
    var joke : Joke = Joke("UI Placeholder")
    var jokeList = listOf<Joke>()

    println("Getting new joke")
    viewModel.getAndInsertNewJoke()
    viewModel.refreshJokes()

    var jokeObserver = Observer<List<Joke>> { jokes ->
        joke = jokes.get(jokes.lastIndex)
        jokeList = jokes.minus(joke)
    }

    viewModel.jokes.observeForever(jokeObserver)

    Column {
        Text(text = "Recent joke : ${joke.punchline}")
        Text(text = "Previous jokes : ")
        Column (
            modifier = modifier.verticalScroll(rememberScrollState())
        ) {
            jokeList.forEach { joke ->
                Text(text = joke.punchline)
            }
        }
    }
}