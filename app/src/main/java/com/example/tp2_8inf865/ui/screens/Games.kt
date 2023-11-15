package com.example.tp2_8inf865.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tp2_8inf865.ApiService
import com.example.tp2_8inf865.data.Joke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@Composable
fun GameScreen(StartValue: Int = 90) {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.yomomma.info")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    val jokeAPI = retrofit.create(ApiService::class.java)
    val call = jokeAPI.getJoke()
    var jokeGiven : Joke?

    var text by remember { mutableStateOf("") }

    /**
     * Request API to get users Data
     */
    if(text.isBlank())
    {
        call.enqueue(object : Callback<Joke> {

            override fun onResponse(p0: Call<Joke>, res: Response<Joke>) {
                jokeGiven = res.body()
                text = jokeGiven?.punchline ?: "No joke found"
            }

            override fun onFailure(p0: Call<Joke>, p1: Throwable) {
                println(p1.toString())
            }
        })
    }
        Text(text =  text,
            Modifier.padding(start = StartValue.dp))


    //Text(text = "Here will be everything that is related to sessions that are currently being played.",
    //Modifier.padding(start = StartValue.dp))
}