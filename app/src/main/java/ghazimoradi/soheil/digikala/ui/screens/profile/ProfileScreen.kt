package ghazimoradi.soheil.digikala.ui.screens.profile

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.App
import ghazimoradi.soheil.digikala.MainActivity
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel
import ghazimoradi.soheil.digikala.util.Constants.PERSIAN_LANG
import ghazimoradi.soheil.digikala.util.Constants.ENGLISH_LANG

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val activity = App.currentActivity
        Text("ProfileScreen")
        Button(
            onClick = {
                dataStoreViewModel.saveUserLanguage(PERSIAN_LANG)
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            },
        ) {
            Text("fa")
        }
        Button(
            onClick = {
                dataStoreViewModel.saveUserLanguage(ENGLISH_LANG)
                activity.apply {
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            },
        ) {
            Text("en")
        }
    }
}