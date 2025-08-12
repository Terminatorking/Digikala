package ghazimoradi.soheil.digikala.ui.screens.address.showAddress

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.address.UserAddress
import ghazimoradi.soheil.digikala.ui.components.detailRow.DetailRow
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.viewModels.DataStoreViewModel

@Composable
fun AddressCard(
    address: UserAddress,
    isFromBasket: Int,
    itemIndex: Int,
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.padding(
            horizontal = MaterialTheme.spacing.biggerSmall,
        )
    ) {

        Row {
            if (isFromBasket >= 0) {
                Column(modifier = Modifier.weight(0.1f)) {
                    RadioButton(
                        selected = isFromBasket == itemIndex,
                        onClick = {
                            dataStore.saveUserAddressIndex(itemIndex.toString())
                            navController.popBackStack()
                        }
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = address.address,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.darkText,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(
                            top = MaterialTheme.spacing.biggerSmall,
                            bottom = MaterialTheme.spacing.biggerMedium
                        )
                )

                DetailRow(
                    painterResource(id = R.drawable.letter),
                    text = address.postalCode,
                    color = MaterialTheme.colorScheme.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )

                DetailRow(
                    painterResource(id = R.drawable.call),
                    text = address.phone,
                    color = MaterialTheme.colorScheme.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )

                DetailRow(
                    painterResource(id = R.drawable.user_outline),
                    text = address.name,
                    color = MaterialTheme.colorScheme.darkText,
                    fontStyle = MaterialTheme.typography.h5
                )
            }
        }
    }

    HorizontalDivider(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small),
        color = MaterialTheme.colorScheme.gray.copy(0.2f)
    )
}