package com.example.tp2_8inf865

import com.example.tp2_8inf865.data.Joke
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getJoke(): Joke
}