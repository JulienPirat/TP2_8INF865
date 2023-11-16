package com.example.tp2_8inf865.data.repository

import android.content.Context
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.data.source.JokeOnlineSource
import com.example.tp2_8inf865.data.source.RoomSource
import javax.inject.Inject


class JokesRepository @Inject constructor(private val onlineDataSource: JokeOnlineSource,
                       private val roomUserDataSource: RoomSource,
                       private val context: Context){

     fun getJokes(): List<Joke> = roomUserDataSource.getCacheJokes(context)

     suspend fun getNewJoke() {
         val jokeGiven = onlineDataSource.getNewJoke()
         roomUserDataSource.addJoke(context, jokeGiven)
    }

    /* A faire a un autre endroid
     suspend fun addJoke(context : Context, joke : Joke){
         roomUserDataSource.addJoke(context, joke)
    }
     */
}