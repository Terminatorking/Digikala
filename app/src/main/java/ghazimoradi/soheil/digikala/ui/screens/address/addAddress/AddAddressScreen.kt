package ghazimoradi.soheil.digikala.ui.screens.address.addAddress

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.address.AddAddressRequest
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.project.ProjectTextField
import ghazimoradi.soheil.digikala.ui.theme.digiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.Constants
import ghazimoradi.soheil.digikala.viewModels.AddressViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddAddressScreen(
    navController: NavHostController,
    viewModel: AddressViewModel = hiltViewModel()
) {

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var postalCode by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    LaunchedEffect(true) {
        viewModel.addNewAddressResponse.collectLatest { addNewAddressResponseResult ->
            when (addNewAddressResponseResult) {
                is NetworkResult.Success -> {
                    navController.popBackStack()
                }

                is NetworkResult.Error -> {
                    Log.e(
                        "3636",
                        "addressListSection error : ${addNewAddressResponseResult.message}"
                    )
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg)
    ) {
        AddAddressHeader(navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.address_details),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.firstname),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            ProjectTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = name,
            ) {
                name = it
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.phone_number),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            ProjectTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = phone,
            ) {
                phone = it
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.postal_code),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            ProjectTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = postalCode,
            ) {
                postalCode = it
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.address),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            ProjectTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = address,
            ) {
                address = it
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        horizontal = MaterialTheme.spacing.small,
                        vertical = MaterialTheme.spacing.small
                    ),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        viewModel.setNewAddress(
                            AddAddressRequest(
                                token = Constants.USER_TOKEN,
                                name = name,
                                phone = phone,
                                postalCode = postalCode,
                                address = address
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.roundedShape.small,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.digiKalaRed),
                ) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.confirm_information),
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
