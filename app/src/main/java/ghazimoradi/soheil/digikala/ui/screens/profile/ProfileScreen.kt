package ghazimoradi.soheil.digikala.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    Profile(navController = navController)
}

@Composable
fun Profile(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainBg)
            .padding(bottom = 60.dp),
    ) {
        item {
            ProfileTopBarSection(navController)
        }
        item {
            ProfileHeaderSection(navController)
        }
    }
}
