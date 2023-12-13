package com.example.tp2_8inf865

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.HiltAndroidApp
import java.io.InputStream
import javax.inject.Inject
import com.firebase.ui.storage.images.FirebaseImageLoader;


@HiltAndroidApp
class App : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    fun registerComponents(context: Context?, registry: Registry) {
        // Register FirebaseImageLoader to handle StorageReference
        registry.append(
            StorageReference::class.java, InputStream::class.java, FirebaseImageLoader.Factory()
        )
    }
}