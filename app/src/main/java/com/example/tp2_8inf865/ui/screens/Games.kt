package com.example.tp2_8inf865.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.tp2_8inf865.SignInActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

fun isLoggedIn() = Firebase.auth.currentUser != null

@Composable
fun GameScreen(StartValue: Int = 90, context: Context) {

    var loggedIn by remember { mutableStateOf(isLoggedIn()) }

    if(loggedIn) {
        val name = Firebase.auth.currentUser!!.displayName
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello $name !" , Modifier.padding(start = StartValue.dp))

            Button(onClick = {
                Firebase.auth.signOut()
                loggedIn = isLoggedIn()
            }) {
                Text(text = "Sign Out")
            }

            Text(text = "Here will be everything that is related to sessions that are currently being played.", Modifier.padding(start = StartValue.dp))
        }

    }else{
        val intent = Intent(context, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }


}