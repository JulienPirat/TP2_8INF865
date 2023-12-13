package com.example.tp2_8inf865.ui.screens

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(StartValue: Int = 90,context : Context, tempLiveData : MutableLiveData<Int>) {


    val storage by remember {
        mutableStateOf(Firebase.storage)
    }
    var storageRef by remember {
        mutableStateOf(storage.reference)
    }
    var temp by remember { mutableIntStateOf(0) }
    var tempObserver = Observer<Int> { t ->
        temp = t
    }

    tempLiveData.observeForever(tempObserver)

    var fileChoser = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {imageUri ->
        if(imageUri != null){
            val imageRef = storageRef.child("images/${imageUri.lastPathSegment}")
            val uploadTask = imageRef.putFile(imageUri)
            uploadTask.addOnFailureListener {
                println("Failed to upload image")
            }.addOnSuccessListener {
                println("Image uploaded successfully")
            }
        }
    }

    var listOfImages = remember {
        mutableStateOf(listOf<Uri>())
    }

    var listUri = remember {
        mutableStateListOf<Uri>()
    }

    LaunchedEffect(Unit)
    {
        val pathRef = storageRef.child("images")
        pathRef.listAll().addOnSuccessListener {
            listUri.clear()
            it.items.forEach { item ->
                item.downloadUrl.addOnSuccessListener { uri ->
                    listUri.add(uri)
                }
            }
        }.addOnFailureListener {
            println("Failed to get url")
        }
    }

    Column (
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = StartValue.dp)
    ) {
        Text(
            text = "Température de : $temp °C",
            Modifier.padding(start = StartValue.dp)
        )
        Button(
            onClick = {
                fileChoser.launch("image/*");

            },
            Modifier.padding(start = StartValue.dp)
        ) {
            Text(text = "Post a meme")
        }
        LazyColumn(
            Modifier.padding(start = StartValue.dp)
        ) {
            items(items = listUri) { uri ->
                GlideImage(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(300.dp)
                        .width(300.dp),
                    model = uri,
                    contentDescription = "Image",
                )
            }
        }
    }
}

