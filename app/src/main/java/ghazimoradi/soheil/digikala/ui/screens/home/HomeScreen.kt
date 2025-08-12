package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.ui.components.extentions.refreshDataFromServer
import ghazimoradi.soheil.digikala.utils.Constants.USER_LANGUAGE
import ghazimoradi.soheil.digikala.utils.LocaleUtils
import ghazimoradi.soheil.digikala.viewModels.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    LaunchedEffect(true) {
        viewModel.refreshDataFromServer()
    }

    PullToRefreshSection(viewModel = viewModel, navController = navController)
}