package ghazimoradi.soheil.digikala.ui.screens.user.userFavoriteProducts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.models.prfile.FavItem
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewModels.UserFavoriteProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserFavoriteProductListSection(
    navController: NavHostController,
    viewModel: UserFavoriteProductListViewModel = hiltViewModel()
) {

    val allFavoriteItems by viewModel.allFavoriteItems.collectAsState(emptyList())

    var selectedItem by remember {
        mutableStateOf(FavItem("", 1, "", "", 1, "", 1.0))
    }

    val modalSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false // Similar to M2's skipHalfExpanded
    )

    var showBottomSheet by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxSize()
    ) {
        if (allFavoriteItems.isEmpty()) {
            item { EmptyUserFavoriteProductListContent() }
        } else {
            item {
                CountUserFavoriteProductsSection(allFavoriteItems.size)
            }

            items(allFavoriteItems) { favItem ->
                UserFavoriteProductItemCard(
                    navController,
                    favItem,
                ) {
                    selectedItem = it
                    showBottomSheet = true
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = modalSheetState,
        ) {
            DeleteFavoriteProductBottomSheetContent(
                onDelete = {
                    viewModel.removeFavoriteItem(selectedItem)
                    showBottomSheet = false
                },
                onCancel = { showBottomSheet = false }
            )
        }
    }
}