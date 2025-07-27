package ghazimoradi.soheil.digikala.ui.screens.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.DIGIPLUS_URL

@Composable
fun ProfileMenuSection(navController: NavHostController) {

    ProfileMenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.digi_plus_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.digi_plus),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGIPLUS_URL}"
        )
    }

    ProfileMenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.digi_fav_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.fav_list),
        haveDivider = true
    ) {
        //  navController.navigate(Screen.FavoriteList.route)
    }

    ProfileMenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.digi_comments_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.my_comments),
        haveDivider = true
    ) {
//        navController.navigate(
//            Screen.AllComment.withArgs(
//                "1",
//               "1",
//               USER_COMMENTS
//           )
//        )
    }

    ProfileMenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.digi_adresses_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.addresses),
        haveDivider = true
    ) {
        // navController.navigate(Screen.ShowAddressScreen.withArgs(-1))
    }

    ProfileMenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.digi_profile_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(36.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        },
        text = stringResource(id = R.string.profile_data),
        haveDivider = false
    ) {
        // navController.navigate(Screen.UserAccount.route)
    }
}