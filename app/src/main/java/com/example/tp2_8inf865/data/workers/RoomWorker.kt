package com.example.tp2_8inf865.data.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tp2_8inf865.data.JokeDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay

@HiltWorker
class RoomWorker @AssistedInject constructor(
    @Assisted context : Context,
    @Assisted params : WorkerParameters,
    private val jokeDao: JokeDao
) : CoroutineWorker(context,params){
    override suspend fun doWork(): Result {

        val test = jokeDao.getAll()

        println(test.toString())

        delay(5000)

        return Result.success()
    }


}