package ghazimoradi.soheil.digikala.ui.screens.category.subCategory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.extentions.getScreenHeight
import ghazimoradi.soheil.digikala.ui.components.loading.Loading
import ghazimoradi.soheil.digikala.ui.components.search.SearchBarSection
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.viewModels.CategoryViewModel

@Composable
fun SubCategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavHostController,
    categoryId: String
) {

    viewModel.getProductByCategory(categoryId)

    val productList = viewModel.productByCategoryList.collectAsLazyPagingItems()

    Column {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.mainBg)
        ) {
            item {
                SearchBarSection()
            }

            items(
                count = productList.itemCount,
                key = productList.itemKey { product -> product._id },
                contentType = productList.itemContentType { "product" }
            ) { index ->
                HorizontalProductCard(productList[index]!!, navController)
            }

            productList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            Loading(getScreenHeight())
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            Loading(30.dp)
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = stringResource(R.string.no_product_found_category),
                                    color = MaterialTheme.colorScheme.darkText,
                                )
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {}
                }
            }
        }
    }
}