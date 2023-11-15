package com.example.tp2_8inf865.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokes")
    suspend fun getAll() : List<Joke>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(joke: Joke)

    @Delete
    suspend fun delete(joke: Joke)

    @Query("DELETE FROM jokes")
    suspend fun deleteAll()
}