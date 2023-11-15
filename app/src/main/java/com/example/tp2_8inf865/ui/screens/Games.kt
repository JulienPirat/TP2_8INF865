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
import com.example.tp2_8inf865.data.AppDatabase
import com.example.tp2_8inf865.data.Joke
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun GameScreen(StartValue: Int = 90) {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.yomomma.info")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    val applicationContext = androidx.compose.ui.platform.LocalContext.current

    val jokeAPI = retrofit.create(ApiService::class.java)
    val call = jokeAPI.getJoke()
    var jokeGiven : Joke?

    val db = AppDatabase.getDatabase(
        applicationContext
    )

    var isSearching = false;

    var jokes : Deferred<List<Joke>>

    var text by remember { mutableStateOf("") }

    /**
     * Request API to get users Data
     */
    if(text.isBlank() && !isSearching)
    {
        isSearching = true;

        call.enqueue(object : Callback<Joke> {

            override fun onResponse(p0: Call<Joke>, res: Response<Joke>) {
                val maPortee = CoroutineScope(Dispatchers.IO)

                jokeGiven = res.body()

                GlobalScope.launch {
                    db.jokeDao().insert(jokeGiven!!)
                }

                jokes = maPortee.async<List<Joke>> {
                    db.jokeDao().getAll()
                }

                jokes.invokeOnCompletion {
                    try{
                        text = jokes.getCompleted().toString()
                    }
                    catch (e: Exception){
                        println(e.toString())
                    }
                    finally {
                        isSearching = false;
                    }
                }
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