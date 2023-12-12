plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

    // Add the Google services Gradle plugin
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.tp2_8inf865"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tp2_8inf865"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))

    // Google Sing In SDK
    implementation("com.google.android.gms:play-services-auth:20.5.0")

    // Firebase SDK
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")

    //  Firebase UI Library
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("com.firebaseui:firebase-ui-database:8.0.2")

    /////////////////////////////

    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("com.google.accompanist:accompanist-insets:0.19.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))

    kapt("androidx.hilt:hilt-compiler:1.0.0")

    implementation("androidx.room:room-common:2.6.0")
    implementation("androidx.hilt:hilt-common:1.1.0")
    implementation("androidx.hilt:hilt-work:1.1.0")
    val room_version = "2.5.0"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    val lifecycle_version = "2.6.2"

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //implementation("com.google.dagger:hilt-android:2.44.1")
    //kapt("com.google.dagger:hilt-android-compiler:2.44.1")

    implementation("com.google.dagger:hilt-android:2.48.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    //implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-jackson:2.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("androidx.compose.material3:material3-window-size-class:1.1.2")
    implementation("androidx.window:window:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

kapt{
    correctErrorTypes = true
}