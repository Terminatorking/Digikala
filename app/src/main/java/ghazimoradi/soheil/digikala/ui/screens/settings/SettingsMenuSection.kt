package ghazimoradi.soheil.digikala.ui.screens.settings

import android.content.Intent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpCenter
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.PestControl
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.App
import ghazimoradi.soheil.digikala.MainActivity
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.components.MenuItem
import ghazimoradi.soheil.digikala.ui.screens.profile.ProfileScreenState
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.GiantsOrange
import ghazimoradi.soheil.digikala.ui.theme.Manatee
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.DIGI_BUG
import ghazimoradi.soheil.digikala.util.Constants.DIGI_FAQ
import ghazimoradi.soheil.digikala.util.Constants.DIGI_PRIVACY
import ghazimoradi.soheil.digikala.util.Constants.DIGI_SCORE
import ghazimoradi.soheil.digikala.util.Constants.DIGI_TERMS
import ghazimoradi.soheil.digikala.util.Constants.DIGI_TURLEARN
import ghazimoradi.soheil.digikala.util.Constants.USER_TOKEN
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel
import ghazimoradi.soheil.digikala.viewmodel.ProfileViewModel

@Composable
fun SettingsMenuSection(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector = Icons.AutoMirrored.Outlined.HelpCenter,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.repetitive_questions),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_FAQ}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector = Icons.Outlined.PrivacyTip,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.privacy),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_PRIVACY}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector =
                    Icons.Outlined.OtherHouses,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.terms_of_use),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TERMS}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector =
                    Icons.Outlined.Call,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.contact_us),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_TURLEARN}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector =
                    Icons.Outlined.PestControl,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.error_report),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_BUG}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector =
                    Icons.Outlined.StarRate,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.rating_to_digikal),
        haveDivider = true
    ) {
        navController.navigate(
            route = Screen.WebView.route + "?url=${DIGI_SCORE}"
        )
    }

    MenuItem(
        icon = {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector =
                    Icons.Outlined.Language,
                contentDescription = ""
            )
        },
        text = stringResource(id = R.string.changing_lang),
        addCompose = { ChangeLanguage() },
        haveDivider = true
    )
    if (USER_TOKEN != "null") {
        MenuItem(
            icon = {
                Icon(
                    Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.DigiKalaRed
                )
            },
            text = stringResource(id = R.string.sign_out),
            color = MaterialTheme.colorScheme.DigiKalaRed,
            addCompose = {},
            haveDivider = false
        ) {
            logOut(navController, dataStore, profileViewModel, basketViewModel)
        }
    }
}

fun logOut(
    navController: NavHostController,
    dataStore: DataStoreViewModel,
    profileViewModel: ProfileViewModel,
    basketViewModel: BasketViewModel
) {
    basketViewModel.deleteAllItems()
    dataStore.apply {
        saveUserToken("null")
        saveUserId("null")
        saveUserPhoneNumber("null")
        saveUserPassword("null")
        saveUserName("null")
        saveUserAddressIndex("0")
    }
    USER_TOKEN = "null"
    profileViewModel.screenState = ProfileScreenState.LOGIN_STATE
    navController.navigate(Screen.Profile.route)
}

@Composable
fun ChangeLanguage(dataStore: DataStoreViewModel = hiltViewModel()) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        val activity = App.currentActivity
        val lang = dataStore.getUserLanguage()
        val checkedState by remember { mutableStateOf(lang) }

        Text(
            color = MaterialTheme.colorScheme.darkText,
            text = stringResource(id = R.string.english)
        )
        Switch(
            colors = SwitchDefaults.colors(
                checkedBorderColor = Transparent,
                uncheckedBorderColor = Transparent,
                uncheckedThumbColor = MaterialTheme.colorScheme.darkText,
                checkedThumbColor = White,
                uncheckedTrackColor = Manatee,
                checkedTrackColor = GiantsOrange
            ),
            modifier = Modifier.padding(
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small
            ),
            checked = (checkedState == "fa"),
            onCheckedChange = {
                dataStore.saveUserLanguage(if (lang == "en") "fa" else "en")
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            }
        )
        Text(
            color = MaterialTheme.colorScheme.darkText,
            text = stringResource(id = R.string.farsi)
        )
    }
}