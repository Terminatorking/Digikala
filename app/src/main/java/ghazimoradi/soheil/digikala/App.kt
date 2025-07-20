package ghazimoradi.soheil.digikala

import android.app.Activity
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    companion object {
       lateinit var currentActitvity: Activity
    }
}