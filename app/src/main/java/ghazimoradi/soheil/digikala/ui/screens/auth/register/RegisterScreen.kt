package ghazimoradi.soheil.digikala.ui.screens.auth.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.TopBarSection
import ghazimoradi.soheil.digikala.ui.components.LoginAndRegisterButton
import ghazimoradi.soheil.digikala.ui.components.ProjectTextField
import ghazimoradi.soheil.digikala.ui.screens.user.profile.ProfileScreenState
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.Constants
import ghazimoradi.soheil.digikala.utils.InputValidation.isValidPassword
import ghazimoradi.soheil.digikala.viewModels.DataStoreViewModel
import ghazimoradi.soheil.digikala.viewModels.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel(),
    navController: NavController,
) {

    val context = LocalContext.current

    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {

                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {

                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhoneNumber(user.phone)
                            Constants.USER_PHONE = user.phone
                            Constants.USER_TOKEN = user.token
                            dataStore.saveUserPassword(profileViewModel.inputPasswordState)

                            dataStore.saveUserName(user.name ?: "null")
                            Constants.USER_NAME = user.name ?: "null"

                            profileViewModel.screenState = ProfileScreenState.PROFILE_STATE
                        }
                    }
                    Toast.makeText(
                        context,
                        loginResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                    profileViewModel.loadingState = false
                }

                is NetworkResult.Error -> {
                    profileViewModel.loadingState = false
                    Log.e("3636", "loginResponse error : ${loginResponse.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarSection(navController)

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.semiLarge
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colorScheme.darkText,
            fontWeight = FontWeight.Bold
        )

        ProjectTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(
                    start = MaterialTheme.spacing.semiLarge,
                    end = MaterialTheme.spacing.semiLarge,
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.semiLarge
                ),
            value = profileViewModel.inputPhoneState,
            placeholder = stringResource(id = R.string.phone_and_email),
            onValueChange = {},
        )

        ProjectTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .padding(
                    start = MaterialTheme.spacing.semiLarge,
                    end = MaterialTheme.spacing.semiLarge,
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.semiLarge
                ),
            value = profileViewModel.inputPasswordState,
            placeholder = stringResource(id = R.string.set_password),
            onValueChange = {
                profileViewModel.inputPasswordState = it
            }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (profileViewModel.loadingState) {
            LoadingButton()
        } else {
            LoginAndRegisterButton(text = stringResource(id = R.string.digikala_login)) {
                if (isValidPassword(profileViewModel.inputPasswordState)) {
                    profileViewModel.login()
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}