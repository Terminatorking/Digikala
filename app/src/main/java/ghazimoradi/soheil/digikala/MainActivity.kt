package ghazimoradi.soheil.digikala

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ghazimoradi.soheil.digikala.navigation.BottomNavigationBar
import ghazimoradi.soheil.digikala.navigation.SetupNavGraph
import ghazimoradi.soheil.digikala.ui.theme.DigikalaTheme
import ghazimoradi.soheil.digikala.util.LocaleUtils
import ghazimoradi.soheil.digikala.util.Constants.PERSIAN_LANG

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                LocaleUtils.setLocale(LocalContext.current, PERSIAN_LANG)

                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                onItemClick = { bottomNavItem ->
                                    navController.navigate(bottomNavItem.route)
                                },
                            )
                        },
                    ) { padding ->
                        SetupNavGraph(navController)
                        Log.i("padding", padding.toString())
                    }
                }
            }
        }
    }
}