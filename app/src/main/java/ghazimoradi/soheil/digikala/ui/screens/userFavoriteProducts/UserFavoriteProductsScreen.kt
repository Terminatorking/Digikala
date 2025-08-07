package ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun UserFavoriteProductsScreen(navController: NavHostController) {
    Scaffold(
        topBar = { UserFavoriteProductTopAppBar(navController) }
    ) {
        Column(
            modifier = Modifier
            .padding(it)
            .background(MaterialTheme.colorScheme.mainBg)
        ) {
            UserFavoriteProductListSection(navController)
        }
    }
}