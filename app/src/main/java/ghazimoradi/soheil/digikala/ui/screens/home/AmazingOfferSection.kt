package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.models.home.AmazingItem
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.theme.digiKalaLightRed
import ghazimoradi.soheil.digikala.viewModels.HomeViewModel
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.digiKalaLightGreen

@Composable
fun AmazingOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    isSuperMarketAmazing: Boolean = false
) {
    var amazingItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }

    val amazingItemResult by viewModel.amazingItems.collectAsState()

    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
        }

        is NetworkResult.Error -> {
            Log.e("3636", "AmazingOfferSection error : ${amazingItemResult.message}")
        }

        is NetworkResult.Loading -> {}
    }

    var superMarketItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }

    val superMarketItemResult by viewModel.superMarketItems.collectAsState()

    when (superMarketItemResult) {
        is NetworkResult.Success -> {
            superMarketItemList = superMarketItemResult.data ?: emptyList()
        }

        is NetworkResult.Error -> {
            Log.e("3636", "superMarketOfferSection error : ${superMarketItemResult.message}")
        }

        is NetworkResult.Loading -> {}
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        LazyRow(
            modifier = if (isSuperMarketAmazing)
                Modifier
                    .background(MaterialTheme.colorScheme.digiKalaLightGreen)
                    .fillMaxWidth()
            else Modifier
                .background(MaterialTheme.colorScheme.digiKalaLightRed)
                .fillMaxWidth()
        ) {
            if (isSuperMarketAmazing) {
                item {
                    AmazingOfferCard(R.drawable.fresh, isSuperMarketAmazing = true)
                }
            } else {
                item {
                    AmazingOfferCard(R.drawable.box)
                }
            }

            if (isSuperMarketAmazing) {
                items(superMarketItemList) { item ->
                    AmazingItem(item = item, navController = navController)
                }
            } else {
                items(amazingItemList) { item ->
                    AmazingItem(item = item, navController = navController)
                }
            }

            item {
                AmazingShowMoreItem()
            }
        }
    }
}