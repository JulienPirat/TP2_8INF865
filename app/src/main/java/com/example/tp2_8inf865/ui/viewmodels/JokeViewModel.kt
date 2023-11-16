package com.example.tp2_8inf865.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.data.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repository: JokesRepository
) : ViewModel() {

    var jokes: MutableLiveData<List<Joke>> = MutableLiveData()

    fun refreshJokes() {
        GlobalScope.launch {
            jokes.postValue(repository.getJokes())
        }
    }

    fun getAndInsertNewJoke()
    {
        val res = GlobalScope.launch {
            repository.getNewJoke()
        }.invokeOnCompletion {
            refreshJokes()
        }
    }
}