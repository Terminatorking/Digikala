package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.models.home.Slider
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.PullToRefresh
import ghazimoradi.soheil.digikala.ui.components.extentions.refreshDataFromServer
import ghazimoradi.soheil.digikala.viewModels.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshSection(viewModel: HomeViewModel, navController: NavController) {

    val coroutineScope = rememberCoroutineScope()

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    val sliderResult by viewModel.slider.collectAsState()

    var loading by remember {
        mutableStateOf(false)
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(sliderResult) {
        when (sliderResult) {
            is NetworkResult.Success -> {
                sliderList = sliderResult.data ?: emptyList()
                loading = false
            }

            is NetworkResult.Error -> {
                loading = false
                Log.e("SliderError", "Top Slider error : ${sliderResult.message}")
            }

            is NetworkResult.Loading -> {
                loading = true
            }
        }
    }

    PullToRefresh(
        onRefresh = {
            loading = true
            isRefreshing = true
            viewModel.refreshDataFromServer()
            coroutineScope.launch {
                delay(1500)
                isRefreshing = false
            }
        },
        isRefreshing = isRefreshing,
        content = {
            HomeScreenContent(
                loading = loading,
                navController = navController,
                sliderList = sliderList
            )
        },
    )
}