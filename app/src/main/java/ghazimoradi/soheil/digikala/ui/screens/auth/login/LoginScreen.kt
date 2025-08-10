package ghazimoradi.soheil.digikala.ui.screens.auth.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.LoginAndRegisterButton
import ghazimoradi.soheil.digikala.ui.components.ProjectTextField
import ghazimoradi.soheil.digikala.ui.components.TopBarSection
import ghazimoradi.soheil.digikala.ui.screens.user.profile.ProfileScreenState
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.InputValidation.isValidEmail
import ghazimoradi.soheil.digikala.utils.InputValidation.isValidPhoneNumber
import ghazimoradi.soheil.digikala.viewModels.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBarSection(navController = navController)
        }

        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }

        item {
            Image(
                modifier = Modifier
                    .size(200.dp),
                painter = painterResource(id = R.drawable.digi_smile),
                contentDescription = "",
            )
        }

        item {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        }

        item {
            Text(
                modifier = Modifier.padding(
                    horizontal = MaterialTheme.spacing.semiLarge
                ),
                style = MaterialTheme.typography.h6,
                text = stringResource(id = R.string.loginTxt),
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold
            )
        }

        item {
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
                onValueChange = {
                    profileViewModel.inputPhoneState = it
                }
            )
        }

        item {
            LoginAndRegisterButton(text = stringResource(id = R.string.digikala_entry)) {
                if (isValidEmail(profileViewModel.inputPhoneState)
                    || isValidPhoneNumber(profileViewModel.inputPhoneState)
                ) {
                    profileViewModel.screenState = ProfileScreenState.REGISTER_STATE
                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.login_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        item {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.searchBarBg,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(top = MaterialTheme.spacing.medium)
            )
        }

        item {
            TermsAndRulesText(
                fullText = stringResource(id = R.string.terms_and_rules_full),
                underlinedText = listOf(
                    stringResource(id = R.string.terms_and_rules),
                    stringResource(id = R.string.privacy_and_rules)
                ),
                textColor = MaterialTheme.colorScheme.semiDarkText,
                fontSize = 10.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}