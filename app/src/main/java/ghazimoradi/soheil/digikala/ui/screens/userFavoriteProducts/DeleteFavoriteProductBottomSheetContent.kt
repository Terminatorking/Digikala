package ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import ghazimoradi.soheil.digikala.viewmodel.UserFavoriteProductListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteFavoriteProductBottomSheetContent(
    viewModel: UserFavoriteProductListViewModel,
    selectedItem: FavItem,
    coroutineScope: CoroutineScope,
    modalSheetState: ModalBottomSheetState,
) {
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