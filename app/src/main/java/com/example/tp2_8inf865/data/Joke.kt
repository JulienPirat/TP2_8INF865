package com.example.tp2_8inf865.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "jokes")
data class Joke @JsonCreator constructor(
    @PrimaryKey @ColumnInfo(name = "punchline") @JsonProperty("joke") val punchline: String
) {
    override fun toString(): String {
        return "Joke : $punchline"
    }
}
