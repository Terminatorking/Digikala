package ghazimoradi.soheil.digikala.ui.screens.category.categoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.ui.components.extentions.refreshDataFromServer
import ghazimoradi.soheil.digikala.viewModels.CategoryViewModel

@Composable
fun CategoryScreen(
    navController: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.refreshDataFromServer()
    }

    PullToRefreshSection(viewModel, navController)
}