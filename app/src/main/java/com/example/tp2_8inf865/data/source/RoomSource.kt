package com.example.tp2_8inf865.data.source

import android.content.Context
import com.example.tp2_8inf865.data.AppDatabase
import com.example.tp2_8inf865.data.Joke


class RoomSource {

    suspend fun getCacheJokes(applicationContext : Context): List<Joke> {

        var jokes : List<Joke>

        val db = AppDatabase.getDatabase(
            applicationContext
        )

        jokes = db.jokeDao().getAll()

        return  jokes
    }

    suspend fun addJoke(applicationContext : Context, joke : Joke){
        val db = AppDatabase.getDatabase(
            applicationContext
        )

        db.jokeDao().insert(joke)
    }
}