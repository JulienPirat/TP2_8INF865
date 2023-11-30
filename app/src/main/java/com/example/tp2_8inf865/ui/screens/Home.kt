package com.example.tp2_8inf865.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

@Composable
fun HomeScreen(StartValue: Int = 90,context : Context, tempLiveData : MutableLiveData<Int>) {

    var temp by remember { mutableStateOf(0) }
    var tempObserver = Observer<Int> { t ->
        temp = t
    }

    tempLiveData.observeForever(tempObserver)

    Column {
        Text(text = "Home, there will be a summary of the current game, and a list of the last played games.",
            Modifier.padding(start = StartValue.dp))

        //var temp = 20
        Text(text = "Température de : $temp °C",
            Modifier.padding(start = StartValue.dp))
    }

}

