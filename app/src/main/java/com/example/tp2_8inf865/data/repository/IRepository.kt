package com.example.tp2_8inf865.data.repository

import android.content.Context
import com.example.tp2_8inf865.data.Joke

interface IRepository {
    suspend fun getJokes(context : Context) : List<Joke>

    fun getNewJoke() : Joke?

    suspend fun addJoke(context : Context, joke : Joke)
}