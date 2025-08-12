package ghazimoradi.soheil.digikala.ui.screens.user.userFavoriteProducts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaDarkRed
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun DeleteFavoriteProductBottomSheetContent(
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
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

                    onDelete()
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
                    onCancel()
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