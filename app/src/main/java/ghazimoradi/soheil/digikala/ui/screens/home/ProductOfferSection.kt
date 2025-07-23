package ghazimoradi.soheil.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.OurLoading
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocate
import ghazimoradi.soheil.digikala.viewmodel.HomeViewModel

/**
 * if `isMostVisited = false` shows bestSeller products
 * and if `isMostVisited = true` shows mostVisited products
 */
@Composable
fun ProductOfferSection(
    viewModel: HomeViewModel = hiltViewModel(),
    isMostVisited: Boolean = false
) {
    var bestSellerOfferList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val bestSellerOfferResult by viewModel.bestSellerItems.collectAsState()

    when (bestSellerOfferResult) {
        is NetworkResult.Success -> {
            bestSellerOfferList = bestSellerOfferResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("3636", "BestSellerOfferSection error : ${bestSellerOfferResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    var mostVisitedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    val mostVisitedResult by viewModel.mostVisitedItems.collectAsState()

    when (mostVisitedResult) {
        is NetworkResult.Success -> {
            mostVisitedList = mostVisitedResult.data ?: emptyList()
        }

        is NetworkResult.Error -> {
            Log.e("3636", "MostVisitedOfferSection error : ${mostVisitedResult.message}")
        }

        is NetworkResult.Loading -> {}
    }

    if (loading) {
        OurLoading()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)
        ) {

            Text(
                text = if (isMostVisited)
                    stringResource(R.string.most_visited_products)
                else stringResource(R.string.best_selling_products),

                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.medium)
                    .height(250.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (isMostVisited) {
                    itemsIndexed(mostVisitedList) { index, item ->
                        ProductHorizontalCard(
                            name = item.name,
                            id = digitByLocate((index + 1).toString()),
                            imageUrl = item.image
                        )
                    }
                } else {
                    itemsIndexed(bestSellerOfferList) { index, item ->
                        ProductHorizontalCard(
                            name = item.name,
                            id = digitByLocate((index + 1).toString()),
                            imageUrl = item.image
                        )
                    }
                }
            }
        }
    }
}