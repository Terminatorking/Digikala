package ghazimoradi.soheil.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ghazimoradi.soheil.digikala.ui.screens.basket.BasketScreen
import ghazimoradi.soheil.digikala.ui.screens.category.CategoryScreen
import ghazimoradi.soheil.digikala.ui.screens.checkout.CheckoutScreen
import ghazimoradi.soheil.digikala.ui.screens.home.HomeScreen
import ghazimoradi.soheil.digikala.ui.screens.home.WebPageScreen
import ghazimoradi.soheil.digikala.ui.screens.profile.ProfileScreen
import ghazimoradi.soheil.digikala.ui.screens.settings.SettingsScreen
import ghazimoradi.soheil.digikala.ui.screens.splash.SplashScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }
        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }
        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }
        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("url")?.let {
                WebPageScreen(navController = navController, url = it)
            }
        }
    }
}