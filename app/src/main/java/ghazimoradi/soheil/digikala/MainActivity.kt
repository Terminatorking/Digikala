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
import dagger.hilt.android.AndroidEntryPoint
import ghazimoradi.soheil.digikala.navigation.BottomNavigationBar
import ghazimoradi.soheil.digikala.navigation.SetupNavGraph
import ghazimoradi.soheil.digikala.ui.components.AppConfig
import ghazimoradi.soheil.digikala.ui.theme.DigikalaTheme
import ghazimoradi.soheil.digikala.util.LocaleUtils
import ghazimoradi.soheil.digikala.util.Constants.ENGLISH_LANG
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigikalaTheme {
                navController = rememberNavController()
                AppConfig()
                LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

                val direction = if (USER_LANGUAGE == ENGLISH_LANG) {
                    LayoutDirection.Ltr
                } else {
                    LayoutDirection.Rtl
                }

                CompositionLocalProvider(LocalLayoutDirection provides direction) {
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