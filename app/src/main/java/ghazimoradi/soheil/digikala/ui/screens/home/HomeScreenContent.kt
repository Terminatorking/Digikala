package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.models.home.Slider
import ghazimoradi.soheil.digikala.ui.components.extentions.getScreenHeight
import ghazimoradi.soheil.digikala.ui.components.loading.Loading
import ghazimoradi.soheil.digikala.ui.components.search.SearchBarSection
import ghazimoradi.soheil.digikala.ui.components.slider.TopSliderSection
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun HomeScreenContent(
    loading: Boolean,
    navController: NavController,
    sliderList: List<Slider>
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
                TopSliderSection(navController = navController, homeSliders = sliderList)
            }
            item {
                ShowcaseSection(navController = navController)
            }
            item {
                AmazingOfferSection(navController = navController)
            }
            item {
                ProposalCardSection(navController = navController)
            }
            item {
                AmazingOfferSection(
                    navController = navController,
                    isSuperMarketAmazing = true
                )
            }
            item {
                CategoryListSection(navController = navController)
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