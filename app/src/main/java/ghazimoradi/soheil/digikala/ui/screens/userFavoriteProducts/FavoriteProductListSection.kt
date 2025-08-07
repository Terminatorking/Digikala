package ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.prfile.FavItem
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaDarkRed
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewmodel.FavoriteListViewModel
import kotlinx.coroutines.launch

@Composable
fun FavoriteProductListSection(
    navController: NavHostController,
    viewModel: FavoriteListViewModel = hiltViewModel()
) {

    val allFavoriteItems by viewModel.allFavoriteItems.collectAsState(emptyList())

    val coroutineScope = rememberCoroutineScope()

    var selectedItem by remember {
        mutableStateOf(FavItem("", 1, "", "", 1, "", 1.0))
    }

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    )

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(MaterialTheme.colorScheme.mainBg)
            ) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerSmall))
                Text(
                    color = MaterialTheme.colorScheme.darkText,
                    text = stringResource(id = R.string.sure_to_remove_fav_item),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.biggerSmall)
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedButton(
                        onClick = {
                            viewModel.removeFavoriteItem(selectedItem)
                            coroutineScope.launch {
                                if (modalSheetState.isVisible) {
                                    modalSheetState.hide()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.DigiKalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.remove_fav_item),
                            color = MaterialTheme.colorScheme.DigiKalaDarkRed,
                            style = MaterialTheme.typography.h5
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                if (modalSheetState.isVisible) {
                                    modalSheetState.hide()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.DigiKalaRed),
                        shape = MaterialTheme.roundedShape.semiSmall,
                        modifier = Modifier.size(width = 150.dp, height = 50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            color = MaterialTheme.colorScheme.DigiKalaDarkRed,
                            style = MaterialTheme.typography.h3
                        )
                    }
                }
            }
        }
    ) {
        Column {
            CountFavoriteSection(allFavoriteItems.size)

            LazyColumn(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxSize()
            ) {
                if (allFavoriteItems.isEmpty()) {
                    item { EmptyFavoriteProductListContent() }
                } else {
                    items(allFavoriteItems) { favItem ->
                        FavoriteItemCard(
                            navController,
                            favItem,
                            coroutineScope,
                            modalSheetState
                        ) {
                            selectedItem = it
                        }
                    }
                }
            }
        }
    }
}