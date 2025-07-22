package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.model.home.AmazingItem
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaLightRed
import ghazimoradi.soheil.digikala.viewmodel.HomeViewModel
import ghazimoradi.soheil.digikala.R

@Composable
fun AmazingOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.DigiKalaLightRed)
    ) {

        LazyRow(
            modifier = Modifier.background(MaterialTheme.colors.DigiKalaLightRed)
        ) {

            item {
                AmazingOfferCard(R.drawable.box)
            }

            items(amazingItemList) { item ->
                AmazingItem(item = item, navController = navController)
            }

            item {
                AmazingShowMoreItem()
            }
        }
    }
}