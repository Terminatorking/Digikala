plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

android {
    namespace = "ghazimoradi.soheil.digikala"
    compileSdk = 36

    defaultConfig {
        applicationId = "ghazimoradi.soheil.digikala"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

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
    }
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
    //Material2
    implementation("androidx.compose.material:material:1.8.3")
//    //Retrofit
//    implementation ("com.squareup.retrofit2:retrofit:3.0.0")
//    implementation ("com.squareup.retrofit2:converter-gson:3.0.0")
//    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
//    implementation ("com.google.code.gson:gson:2.10.1")
//    //Room database
//    implementation ("androidx.room:room-runtime:$room_version")
//    kapt ("androidx.room:room-compiler:$room_version")
//    implementation ("androidx.room:room-ktx:$room_version")
//    //Datastore
//    implementation ("androidx.datastore:datastore-preferences:$datastore_version")
//    //Hilt
//    implementation ("com.google.dagger:hilt-android:$hilt_version")
//    kapt ("com.google.dagger:hilt-compiler:$hilt_version")
//    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    //Compose Navigation
//    implementation ("androidx.navigation:navigation-compose:2.5.3")
//    //Animation
//    implementation ("com.airbnb.android:lottie-compose:5.2.0")
//    //Coil Load Image From Url
//    implementation ("io.coil-kt:coil-compose:2.3.0")
//    //Swipe refresh
//    implementation ("com.google.accompanist:accompanist-swiperefresh:0.30.0")
//    //System Ui Controller
//    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.0")
//    //Accompanist-Pager
//    implementation ("com.google.accompanist:accompanist-pager:0.30.0")
//    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.0")
}

kapt {
    correctErrorTypes = true
}