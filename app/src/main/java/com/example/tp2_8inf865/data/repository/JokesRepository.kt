package com.example.tp2_8inf865.data.repository

import android.content.Context
import com.example.tp2_8inf865.data.Joke
import com.example.tp2_8inf865.data.source.JokeOnlineSource
import com.example.tp2_8inf865.data.source.RoomSource


class JokesRepository : IRepository {
    val jokeOnlineSource = JokeOnlineSource()
    val RoomSource = RoomSource()

    override suspend fun getJokes(context : Context): List<Joke> {
        return RoomSource.getCacheJokes(context)
    }

    override fun getNewJoke(): Joke? {
        return jokeOnlineSource.getJoke("https://api.yomomma.info")
    }

    override suspend fun addJoke(context : Context, joke : Joke){
        RoomSource.addJoke(context, joke)
    }
}