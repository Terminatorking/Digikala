package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ghazimoradi.soheil.digikala.ui.theme.White
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
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    SwipeRefresh(
        swipeRefreshState,
        onRefresh = {
            refreshScope.launch {

            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(White)
                .padding(bottom = 60.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {
            LaunchedEffect(true) {
                homeViewModel.getAllDataFromServer()
            }
            TopSliderSection()
        }
    }
}