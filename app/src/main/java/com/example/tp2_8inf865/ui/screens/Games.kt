package com.example.tp2_8inf865.ui.screens

import androidx.compose.runtime.Composable
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun GameScreen(StartValue: Int = 90) {
/*
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
*/

    //Text(text = "Here will be everything that is related to sessions that are currently being played.",
    //Modifier.padding(start = StartValue.dp))
}