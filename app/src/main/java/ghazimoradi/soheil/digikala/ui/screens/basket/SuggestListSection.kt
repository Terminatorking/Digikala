package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.basket.CartItem
import ghazimoradi.soheil.digikala.data.models.basket.CartStatus
import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import ghazimoradi.soheil.digikala.ui.components.loading.Loading
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewModels.BasketViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestListSection(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel(),
    loading: Boolean,
    suggestedList: List<StoreProduct>
) {
    if (loading) {
        Loading()
    } else {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.spacing.small)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.suggestion_for_you),
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.darkText,
        )

        FlowRow(
            maxItemsInEachRow = 2,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Start
        ) {
            for (item in suggestedList) {
                SuggestionItemCard(item, navController) { storeProduct ->
                    viewModel.insertCartItem(
                        CartItem(
                            storeProduct._id,
                            storeProduct.name,
                            storeProduct.seller,
                            storeProduct.price,
                            storeProduct.discountPercent,
                            storeProduct.image,
                            1,
                            CartStatus.CURRENT_CART
                        )
                    )
                }
            }
        }
    }
}