import org.gradle.kotlin.dsl.libs

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)  // Firebase Plugin
    alias(libs.plugins.google.firebase.crashlytics) // Firebase Crashlytics
}



apply(plugin = "com.google.gms.google-services") // Apply Firebase Plugin
apply(plugin = "com.google.firebase.crashlytics") // Apply Firebase Crashlytics

android {
    namespace = "com.example.niharika_all_for_one"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.niharika_all_for_one"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "0.2.0.0_beta"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    buildFeatures {
        viewBinding = true
    }
    buildToolsVersion = "36.0.0"

//
//    aaptOptions {
//        ignoreAssetsPattern = "!.jpg:!.png:!.gif"
//    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

//    implementation(libs.google.firebase.analytics.ktx)
//    implementation(libs.google.firebase.crashlytics.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    implementation(libs.firebase.analytics)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))  // Firebase Authentication

    // Declare the dependencies for the desired Firebase products without specifying versions
    // For example, declare the dependencies for Firebase Authentication and Cloud Firestore
//    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore") // Firestore ✅
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.29")
    implementation("com.firebaseui:firebase-ui-firestore:9.0.0")


    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    // https://firebase.google.com/docs/android/setup#available-libraries

    implementation("com.google.firebase:firebase-auth:24.0.1") // Latest Firebase Auth
    implementation("com.google.android.play:integrity:1.4.0") // Play Integrity API
    implementation("com.google.firebase:firebase-database:22.0.0")
    implementation("com.google.firebase:firebase-firestore:26.0.0")

    implementation("com.google.firebase:firebase-analytics:23.0.0")
    implementation("com.google.firebase:firebase-database-ktx:21.0.0")
    implementation("com.google.firebase:firebase-analytics-ktx:22.5.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.2.1")
    implementation("com.google.firebase:firebase-crashlytics:20.0.0")
    implementation("com.google.firebase:firebase-crashlytics-ktx:19.4.4")
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.29")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation ("com.google.firebase:firebase-storage-ktx")

    // ML Kit OCR
    implementation ("com.google.mlkit:text-recognition:16.0.1")

    // Voice Recording
    implementation ("androidx.core:core-ktx:1.17.0")

    // Glide already added earlier
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    implementation("androidx.compose.ui:ui:1.9.0")
    implementation("androidx.compose.material:material:1.9.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.9.0")

    implementation("androidx.recyclerview:recyclerview:1.4.0") // Recycler View
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.2.0")



    implementation(platform(libs.firebase.bom)) // Import Firebase BOM
    implementation(platform("androidx.compose:compose-bom:2025.08.00")) // Jetpack Compose
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
}