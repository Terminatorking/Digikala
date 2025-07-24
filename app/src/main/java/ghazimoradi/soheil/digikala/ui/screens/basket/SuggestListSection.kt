package ghazimoradi.soheil.digikala.ui.screens.basket

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.BasketViewModel
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.basket.CartItem
import ghazimoradi.soheil.digikala.data.model.basket.CartStatus
import ghazimoradi.soheil.digikala.ui.components.OurLoading

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestListSection(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    viewModel.getAllDataFromServer()

    var suggestedList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val suggestedItemResult by viewModel.suggestedList.collectAsState()
    when (suggestedItemResult) {
        is NetworkResult.Success -> {
            suggestedList = suggestedItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("3636", "SuggestListSection error : ${suggestedItemResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        OurLoading()
    } else {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.small)
                .background(MaterialTheme.colors.searchBarBg)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.suggestion_for_you),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
        )

        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {

            for (item in suggestedList) {
                SuggestionItemCard(item, navController) {
                    viewModel.insertCartItem(
                        CartItem(
                            it._id,
                            it.name,
                            it.seller,
                            it.price,
                            it.discountPercent,
                            it.image,
                            1,
                            CartStatus.CURRENT_CART
                        )
                    )
                }
            }
        }
    }
}