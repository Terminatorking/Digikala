package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun TopBarSection(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                //  navController.navigate(Screen.Setting.route)
            },
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.digi_settings
                ), contentDescription = "",
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small
                    )
                    .size(MaterialTheme.spacing.semiLarge),
                tint = MaterialTheme.colors.icon
            )
        }

        IconButton(
            onClick = {
                navController.navigate(Screen.Home.route)
            },
        ) {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small),
                tint = MaterialTheme.colors.icon
            )
        }
    }
}