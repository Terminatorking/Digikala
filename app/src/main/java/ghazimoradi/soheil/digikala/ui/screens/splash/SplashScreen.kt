package ghazimoradi.soheil.digikala.ui.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.remote.CheckConnection.isNetworkAvailable
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.extentions.showToast
import ghazimoradi.soheil.digikala.utils.Constants.isFromPurchase
import ghazimoradi.soheil.digikala.utils.Constants.purchaseOrderId
import ghazimoradi.soheil.digikala.utils.Constants.purchasePrice
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val context = LocalContext.current
    val isNetworkAvailable = remember {
        isNetworkAvailable(context)
    }

    Splash(isNetworkAvailable) {
        if (isNetworkAvailable(context)) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        } else {
            context.getString(R.string.check_net).showToast(context)
        }
    }

    LaunchedEffect(true) {
        delay(2500)
        if (isNetworkAvailable) {
            if (isFromPurchase) {
                navController.navigate(
                    Screen.ConfirmPurchase.withArgs(
                        purchaseOrderId, purchasePrice
                    )
                ) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            } else {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}