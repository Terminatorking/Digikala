package ghazimoradi.soheil.digikala.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun SettingsScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainBg)
            .verticalScroll(rememberScrollState())
    ) {
        SettingsHeader(navController)

        SettingsMenuSection(navController)

        Spacer(modifier = Modifier.weight(1f))

        SettingsBranding()
    }
}