package com.example.tp2_8inf865

import android.app.Application
import com.example.tp2_8inf865.data.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()
{
    val db by lazy { AppDatabase.getDatabase(context = applicationContext) }
}