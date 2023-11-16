package com.example.tp2_8inf865.data.module

import android.content.Context
import androidx.room.Room
import com.example.tp2_8inf865.data.AppDatabase
import com.example.tp2_8inf865.data.JokeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideJokesDao(database: AppDatabase): JokeDao {
        return database.jokeDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "word_database"
        ).build()
    }
}