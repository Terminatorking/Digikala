package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel

@Composable
fun NextShoppingList(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    Text("Next Shopping List")
}