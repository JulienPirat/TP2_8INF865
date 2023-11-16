package com.example.tp2_8inf865.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.data.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {
    suspend fun getJokes() : List<Joke> = repository.getJokes()

    suspend fun getNewJoke() : Joke? = repository.getNewJoke()
}