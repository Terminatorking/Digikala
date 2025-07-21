package ghazimoradi.soheil.digikala

import androidx.activity.ComponentActivity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
       lateinit var currentActivity: ComponentActivity
    }
}