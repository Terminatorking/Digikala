package ghazimoradi.soheil.digikala

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ghazimoradi.soheil.digikala.navigation.SetupNavGraph
import ghazimoradi.soheil.digikala.ui.theme.DigikalaTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                Scaffold(bottomBar = {}) {
                    SetupNavGraph(navController)
                    Log.i("padding", it.toString())
                }
            }
        }
    }
}