package ghazimoradi.soheil.digikala.ui.screens.category.categoryScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.category.Sub
import ghazimoradi.soheil.digikala.ui.components.extentions.getScreenHeight
import ghazimoradi.soheil.digikala.ui.components.loading.Loading

@Composable
fun SubCategorySection(
    navController: NavHostController,
    toolList: List<Sub>,
    digitalList: List<Sub>,
    mobileList: List<Sub>,
    supermarketList: List<Sub>,
    fashionList: List<Sub>,
    nativeList: List<Sub>,
    toyList: List<Sub>,
    beautyList: List<Sub>,
    homeList: List<Sub>,
    bookList: List<Sub>,
    sportList: List<Sub>,
    loading: Boolean
) {

    if (loading) {
        Loading(getScreenHeight())
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            CategoryItem(
                navController = navController,
                categoryId = "tool",
                stringResource(id = R.string.industrial_tools_and_equipment),
                toolList
            )
            CategoryItem(
                navController = navController,
                categoryId = "digital",
                title = stringResource(id = R.string.digital_goods),
                subList = digitalList
            )
            CategoryItem(
                navController = navController,
                categoryId = "mobile",
                title = stringResource(id = R.string.mobile),
                subList = mobileList
            )
            CategoryItem(
                navController = navController,
                categoryId = "fashion",
                title = stringResource(id = R.string.fashion_and_clothing),
                subList = fashionList
            )
            CategoryItem(
                navController = navController,
                categoryId = "supermarket",
                title = stringResource(id = R.string.supermarket_product),
                subList = supermarketList
            )
            CategoryItem(
                navController = navController,
                categoryId = "native",
                title = stringResource(id = R.string.native_and_local_products),
                subList = nativeList
            )
            CategoryItem(
                navController = navController,
                categoryId = "toy",
                title = stringResource(id = R.string.toys_children_and_babies),
                subList = toyList
            )
            CategoryItem(
                navController = navController,
                categoryId = "beauty",
                title = stringResource(id = R.string.beauty_and_health),
                subList = beautyList
            )
            CategoryItem(
                navController = navController,
                categoryId = "home",
                title = stringResource(id = R.string.home_and_kitchen),
                subList = homeList
            )
            CategoryItem(
                navController = navController,
                categoryId = "book",
                title = stringResource(id = R.string.books_and_stationery),
                subList = bookList
            )
            CategoryItem(
                navController = navController,
                categoryId = "sport",
                title = stringResource(id = R.string.sports_and_travel),
                subList = sportList
            )
        }
    }
}
