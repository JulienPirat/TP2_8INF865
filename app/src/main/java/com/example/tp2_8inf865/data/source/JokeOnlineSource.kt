package com.example.tp2_8inf865.data.source

import com.example.tp2_8inf865.ApiService
import com.example.tp2_8inf865.data.Joke
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

class JokeOnlineSource{

    @Singleton
    fun getJoke(BaseURL: String): Joke? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        var JokeApiService = retrofit.create(ApiService::class.java)

        val call = JokeApiService.getJoke()

        var jokeGiven : Joke?

        jokeGiven = call.execute().body()
/*
        call.enqueue(object : Callback<Joke> {
            override fun onResponse(p0: Call<Joke>, res: Response<Joke>) {
                //val maPortee = CoroutineScope(Dispatchers.IO)

                jokeGiven = res.body()

                /*
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

                 */
            }

            override fun onFailure(p0: Call<Joke>, p1: Throwable) {
                println(p1.toString())
            }
        })
*/
        return jokeGiven
    }
}