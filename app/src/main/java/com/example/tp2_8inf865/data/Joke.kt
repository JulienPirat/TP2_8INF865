package com.example.tp2_8inf865.data

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class Joke @JsonCreator constructor(
    @JsonProperty("joke") val punchline: String
) {
    override fun toString(): String {
        return "Joke : $punchline"
    }
}
