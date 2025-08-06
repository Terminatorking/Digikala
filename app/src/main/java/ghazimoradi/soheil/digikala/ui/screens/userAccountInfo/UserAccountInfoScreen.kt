package ghazimoradi.soheil.digikala.ui.screens.userAccountInfo

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
import ghazimoradi.soheil.digikala.data.model.prfile.SetUserNameRequest
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.ProjectTextField
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants
import ghazimoradi.soheil.digikala.viewmodel.DataStoreViewModel
import ghazimoradi.soheil.digikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserAccountInfoScreen(
    navController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    var name = ""
    var family = ""

    if (Constants.USER_NAME != "null") {
        val nameParts = Constants.USER_NAME.split(" - ")
        name = nameParts[0]
        family = nameParts[1]
    }

    var firstName by remember {
        mutableStateOf(name)
    }

    var lastName by remember {
        mutableStateOf(family)
    }

    LaunchedEffect(true) {
        profileViewModel.setUserNameResponse.collectLatest { setUserNameResponse ->
            when (setUserNameResponse) {
                is NetworkResult.Success -> {
                    dataStoreViewModel.saveUserName("$firstName - $lastName")
                    Constants.USER_NAME = "$firstName - $lastName"
                    navController.popBackStack()
                }

                is NetworkResult.Error -> {
                    profileViewModel.loadingState = false
                    Log.e("3636", "setUserNameResponse error : ${setUserNameResponse.message}")
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

        UserAccountInfoHeader(navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.enter_name_lastname),
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
                value = firstName,
            ) {
                firstName = it
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

            Text(
                text = stringResource(id = R.string.lastname),
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))

            ProjectTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                value = lastName,
            ) {
                lastName = it
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
                        profileViewModel.setUserName(
                            SetUserNameRequest(
                                token = Constants.USER_TOKEN,
                                name = "$firstName - $lastName"
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.roundedShape.small,
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.DigiKalaRed),
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