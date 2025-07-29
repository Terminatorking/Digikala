package ghazimoradi.soheil.digikala.ui.screens.checkout

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.address.UserAddress
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.IconWithRotate
import ghazimoradi.soheil.digikala.ui.components.OurLoading
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.USER_TOKEN
import ghazimoradi.soheil.digikala.viewmodel.AddressViewModel
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel

@Composable
fun CartAddressSection(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel(),
    onAddressReady: (List<UserAddress>) -> Unit
) {

    val addressIndex = if (dataStore.getUserAddressIndex().toString() == "null") 0
    else dataStore.getUserAddressIndex().toString().toInt()

    var addressList by remember {
        mutableStateOf<List<UserAddress>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var address = stringResource(id = R.string.no_address)
    var addressName = ""
    var addressBtnText = stringResource(id = R.string.add_address)

    LaunchedEffect(true) {
        viewModel.getUserAddressList(USER_TOKEN)
    }

    val addressListResult by viewModel.userAddressList.collectAsState()

    when (addressListResult) {
        is NetworkResult.Success -> {
            addressList = addressListResult.data ?: emptyList()
            if (addressList.isNotEmpty()) {
                onAddressReady(addressList)
                address = addressList[addressIndex].address
                addressBtnText = stringResource(id = R.string.change_address)
                addressName = addressList[addressIndex].name
            }
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CartAddressSection error : ${addressListResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading) {
        OurLoading(100.dp)
    } else {
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                tint = MaterialTheme.colors.icon,
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                modifier = Modifier
                    .size(22.dp)
                    .weight(0.15f)
                    .align(Alignment.CenterVertically),
            )

            Column(
                modifier = Modifier
                    .weight(0.85f)
                    .padding(vertical = MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.send_to),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.darkText,
                )

                Text(
                    color = MaterialTheme.colors.darkText,
                    text = address,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 3
                )

                Text(
                    color = MaterialTheme.colors.darkText,
                    text = addressName,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    .clickable {
                        // navController.navigate(Screen.ShowAddressScreen.withArgs(addressIndex))
                    },
                text = addressBtnText,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.extraSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.cyan,
            )

            IconWithRotate(
                width = 15.dp,
                height = 15.dp,
                painter = painterResource(id = R.drawable.arrow_back),
                tint = MaterialTheme.colors.cyan,
            )
        }
    }

    HorizontalDivider(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small),
        color = MaterialTheme.colors.gray.copy(0.4f),
    )
}