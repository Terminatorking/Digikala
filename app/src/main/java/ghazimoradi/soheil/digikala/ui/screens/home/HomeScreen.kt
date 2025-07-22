package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE
import ghazimoradi.soheil.digikala.util.LocaleUtils
import ghazimoradi.soheil.digikala.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController = navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    LaunchedEffect(true) {
        refreshDataFromServer(viewModel = homeViewModel)
    }
    SwipeRefreshSection(viewModel = homeViewModel, navController = navController)
}

@Composable
@Suppress("Deprecation")
fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel = viewModel)
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize().padding(bottom = 60.dp)
        ) {
            item {
                SearchBarSection()

            }
            item {
                TopSliderSection()
            }
            item {
                ShowcaseSection(navController = navController)
            }
            item {
                AmazingOfferSection(navController = navController)
            }
            item{
                ProposalCardSection()
            }
            item {
                AmazingOfferSection(navController = navController, isSuperMarketAmazing = true)
            }
            item {
                CategoryListSection()
            }
        }
    }
}

private fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getAllDataFromServer()
}