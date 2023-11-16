package com.example.tp2_8inf865.data.source

import com.example.tp2_8inf865.ApiService
import com.example.tp2_8inf865.data.Joke
import javax.inject.Inject

class JokeOnlineSource @Inject constructor(
    private val jokeApiService: ApiService
){
    suspend fun getNewJoke() : Joke = jokeApiService.getJoke()
}