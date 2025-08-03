package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ghazimoradi.soheil.digikala.data.model.home.Slider
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.Loading
import ghazimoradi.soheil.digikala.ui.components.SearchBarSection
import ghazimoradi.soheil.digikala.ui.components.TopSliderSection
import ghazimoradi.soheil.digikala.ui.components.getScreenHeight
import ghazimoradi.soheil.digikala.ui.components.refreshDataFromServer
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE
import ghazimoradi.soheil.digikala.util.LocaleUtils
import ghazimoradi.soheil.digikala.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController = navController)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, USER_LANGUAGE)

    LaunchedEffect(true) {
        viewModel.refreshDataFromServer()
    }

    SwipeRefreshSection(viewModel = viewModel, navController = navController)
}

@Composable
@Suppress("Deprecation")
fun SwipeRefreshSection(viewModel: HomeViewModel, navController: NavController) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    var sliderList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }

    val sliderResult by viewModel.slider.collectAsState()

    var loading by remember {
        mutableStateOf(false)
    }

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

    SwipeRefresh(
        swipeRefreshState,
        onRefresh = {
            viewModel.refreshDataFromServer()
        },
    ) {
        if (loading) {
            Loading(getScreenHeight())
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.mainBg)
                    .padding(bottom = 60.dp)
            ) {
                item {
                    SearchBarSection()
                }
                item {
                    TopSliderSection(homeSliders = sliderList)
                }
                item {
                    ShowcaseSection(navController = navController)
                }
                item {
                    AmazingOfferSection(navController = navController)
                }
                item {
                    ProposalCardSection()
                }
                item {
                    AmazingOfferSection(navController = navController, isSuperMarketAmazing = true)
                }
                item {
                    CategoryListSection()
                }
                item {
                    CenterBannerSection(1)
                }
                item {
                    ProductOfferSection(navController = navController)
                }
                item {
                    CenterBannerSection(2)
                }
                item {
                    MostFavoriteProductSection(navController = navController)
                }
                item {
                    CenterBannerSection(3)
                }
                item {
                    ProductOfferSection(navController = navController, isMostVisited = true)
                }
                item {
                    CenterBannerSection(4)
                }
                item {
                    CenterBannerSection(5)
                }
                item {
                    MostDiscountedSection(navController = navController)
                }
            }
        }
    }
}