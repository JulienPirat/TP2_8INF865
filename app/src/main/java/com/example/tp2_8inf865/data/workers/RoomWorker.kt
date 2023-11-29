package com.example.tp2_8inf865.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.tp2_8inf865.ui.viewmodels.JokeViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class RoomWorker @Inject constructor(private val context : Context, private var viewModel: JokeViewModel,params : WorkerParameters) : CoroutineWorker(context,params){
    override suspend fun doWork(): Result {

        viewModel.refreshJokes();

        delay(5000)

        return Result.success()
    }


}