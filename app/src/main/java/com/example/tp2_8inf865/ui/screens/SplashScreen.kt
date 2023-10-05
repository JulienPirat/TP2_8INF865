package com.example.tp2_8inf865.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tp2_8inf865.MainActivity
import com.example.tp2_8inf865.R
import com.example.tp2_8inf865.ui.theme.TP2_8INF865Theme
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2_8INF865Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SplashScreenImage()
                    LaunchedEffect(key1 = true){
                        delay(2000)
                        Intent(applicationContext,MainActivity::class.java).also {
                            //it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //This didn't worked so instead configured the activity in the manifest to hold no history
                            startActivity(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreenImage() {
    Image(painter = painterResource(R.drawable.d20), contentDescription = null, modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TP2_8INF865Theme {
        SplashScreenImage()
    }
}