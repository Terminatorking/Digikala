package ghazimoradi.soheil.digikala.ui.screens.category.categoryScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.models.category.Sub
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.project.ProjectPullToRefresh
import ghazimoradi.soheil.digikala.ui.components.extentions.refreshDataFromServer
import ghazimoradi.soheil.digikala.ui.components.search.SearchBarSection
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.viewModels.CategoryViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefreshSection(
    viewModel: CategoryViewModel,
    navController: NavHostController
) {

    var toolList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var digitalList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var mobileList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var supermarketList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var fashionList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var nativeList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var toyList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var beautyList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var homeList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var bookList by remember { mutableStateOf<List<Sub>>(emptyList()) }
    var sportList by remember { mutableStateOf<List<Sub>>(emptyList()) }

    var loading by remember { mutableStateOf(false) }
    var isRefreshing by remember { mutableStateOf(false) }

    val subCategoryResult by viewModel.subCategory.collectAsState()

    LaunchedEffect(subCategoryResult) {
        when (subCategoryResult) {
            is NetworkResult.Success -> {
                subCategoryResult.data?.let { subCategory ->
                    toolList = subCategory.tool
                    digitalList = subCategory.digital
                    mobileList = subCategory.mobile
                    supermarketList = subCategory.supermarket
                    fashionList = subCategory.fashion
                    nativeList = subCategory.native
                    toyList = subCategory.toy
                    beautyList = subCategory.beauty
                    homeList = subCategory.home
                    bookList = subCategory.book
                    sportList = subCategory.sport
                }
                loading = false
            }

            is NetworkResult.Error -> {
                loading = false
                Log.e("3636", "AmazingOfferSection error : ${subCategoryResult.message}")
            }

            is NetworkResult.Loading -> {
                loading = true
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()

    ProjectPullToRefresh(
        isRefreshing = isRefreshing,
        onRefresh = {
            loading = true
            isRefreshing = true
            viewModel.refreshDataFromServer()
            coroutineScope.launch {
                delay(1500)
                isRefreshing = false
            }
        },
        content = {
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
                    SubCategorySection(
                        navController = navController,
                        toolList = toolList,
                        digitalList = digitalList,
                        mobileList = mobileList,
                        supermarketList = supermarketList,
                        fashionList = fashionList,
                        nativeList = nativeList,
                        toyList = toyList,
                        beautyList = beautyList,
                        homeList = homeList,
                        bookList = bookList,
                        sportList = sportList,
                        loading = loading,
                    )
                }
            }
        },
    )
}