package ghazimoradi.soheil.digikala.ui.components.config

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.digiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.statusBarColor

@Composable
fun ChangeStatusBarColor(window: Window, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val statusBarColor = when (navBackStackEntry?.destination?.route) {
        Screen.Splash.route -> MaterialTheme.colorScheme.digiKalaRed.toArgb()
        else -> MaterialTheme.colorScheme.statusBarColor.toArgb()
    }
    // For Android 14 and above
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
        window.decorView.setOnApplyWindowInsetsListener { view, insets ->
            val statusBarInsets = insets.getInsets(WindowInsets.Type.statusBars())
            view.setBackgroundColor(statusBarColor)

            view.setPadding(0, statusBarInsets.top, 0, 0)
            insets
        }
    } else {
        @Suppress("Deprecation")
        // For Android 14 and below
        window.statusBarColor = statusBarColor
    }
}