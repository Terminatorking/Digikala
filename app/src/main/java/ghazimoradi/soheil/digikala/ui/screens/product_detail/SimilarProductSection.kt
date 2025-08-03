package ghazimoradi.soheil.digikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.MostFavoriteProductsOffer
import ghazimoradi.soheil.digikala.ui.components.MostFavoriteProductsShowMore
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SimilarProductSection(
    navController: NavHostController,
    categoryId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    var similarList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    viewModel.getSimilarProducts(categoryId)

    LaunchedEffect(true) {

        viewModel.similarProducts.collectLatest { similarListResult ->
            when (similarListResult) {
                is NetworkResult.Success -> {
                    similarList = similarListResult.data ?: emptyList()
                }

                is NetworkResult.Error -> {
                    Log.e("3636", "SimilarProductSection error : ${similarListResult.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    HorizontalDivider(
        color = MaterialTheme.colors.gray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.similar_product),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )
        }

        LazyRow {
            items(similarList) { item ->
                MostFavoriteProductsOffer(navController, item)
            }
            item {
                MostFavoriteProductsShowMore()
            }
        }
    }
}