package com.example.tp2_8inf865.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    @Query("SELECT * FROM jokes")
    fun getAll() : List<Joke>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(joke: Joke)

    @Delete
    fun delete(joke: Joke)

    @Query("DELETE FROM jokes")
    fun deleteAll()
}