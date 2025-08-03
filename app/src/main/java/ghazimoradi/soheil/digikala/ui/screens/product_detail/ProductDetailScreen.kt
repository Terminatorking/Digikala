package ghazimoradi.soheil.digikala.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.model.product_detail.Comment
import ghazimoradi.soheil.digikala.data.model.product_detail.Price
import ghazimoradi.soheil.digikala.data.model.product_detail.ProductColor
import ghazimoradi.soheil.digikala.data.model.product_detail.ProductDetail
import ghazimoradi.soheil.digikala.data.model.product_detail.SliderImage
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.OurLoading
import ghazimoradi.soheil.digikala.ui.components.getScreenHeight
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
    productId: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {

    var productDetail by remember {
        mutableStateOf(ProductDetail())
    }

    var imageSlider by remember {
        mutableStateOf<List<SliderImage>>(emptyList())
    }
    var productColors by remember {
        mutableStateOf<List<ProductColor>>(emptyList())
    }
    var productComments by remember {
        mutableStateOf<List<Comment>>(emptyList())
    }
    var productPriceList by remember {
        mutableStateOf<List<Price>>(emptyList())
    }

    var categoryId by remember { mutableStateOf("") }

    var description by rememberSaveable { mutableStateOf("") }

    var technicalFeatures by remember { mutableStateOf("") }

    var commentCount by remember { mutableStateOf(0) }

    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.getProductById(productId)
        viewModel.productDetail.collectLatest { netWorkProductDetail ->
            when (netWorkProductDetail) {
                is NetworkResult.Success -> {
                    productDetail = netWorkProductDetail.data!!
                    imageSlider = netWorkProductDetail.data.imageSlider ?: emptyList()
                    productColors = netWorkProductDetail.data.colors ?: emptyList()
                    productComments = netWorkProductDetail.data.comments ?: emptyList()
                    productPriceList = netWorkProductDetail.data.priceList ?: emptyList()
                    categoryId = netWorkProductDetail.data.categoryId ?: ""
                    description = netWorkProductDetail.data.description ?: ""
                    commentCount = netWorkProductDetail.data.commentCount ?: 0
                    technicalFeatures = netWorkProductDetail.data.technicalFeatures.toString()
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${netWorkProductDetail.message}")
                }

                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }
    }

    if (loading) {
        OurLoading(getScreenHeight())
    } else {
        Scaffold(
            bottomBar = {
                ProductDetailBottomBar(productDetail, navController)
            },

            topBar = {
                ProductTopAppBar(navController = navController, product = productDetail)
            }
        ) { padding ->
            Log.i("padding", padding.toString())
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(bottom = 70.dp)
                    .background(MaterialTheme.colors.mainBg)
            ) {

            }
        }
    }
}