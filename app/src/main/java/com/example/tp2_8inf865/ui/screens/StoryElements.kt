package com.example.tp2_8inf865.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.data.workers.RoomWorker
import com.example.tp2_8inf865.ui.viewmodels.JokeViewModel
import java.util.concurrent.TimeUnit

@SuppressLint("RestrictedApi")
@Composable
fun StoryElementsScreen(modifier : Modifier, StartValue: Int = 90, viewModel: JokeViewModel, context: Context){
    var joke : Joke = Joke("UI Placeholder")
    var jokeList = listOf<Joke>()

    println("Getting new joke")
    viewModel.getAndInsertNewJoke()
    //viewModel.refreshJokes()

    val roomWorkerRequest = PeriodicWorkRequestBuilder<RoomWorker>(1,TimeUnit.MINUTES).build()
    WorkManager.getInstance(context).enqueue(roomWorkerRequest)


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