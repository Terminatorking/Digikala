package ghazimoradi.soheil.digikala.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.DIGI_WALLET
import ghazimoradi.soheil.digikala.util.Constants.USER_NAME
import ghazimoradi.soheil.digikala.util.Constants.USER_PHONE
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocate

@Composable
fun ProfileHeaderSection(navController: NavController) {

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    if (USER_NAME != "null") {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = USER_NAME.replace("-", ""),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.darkText,
            style = MaterialTheme.typography.h5
        )
    } else {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // navController.navigate(Screen.UserAccount.route)
                },
            text = stringResource(id = R.string.completion_of_user_information),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.cyan,
            style = MaterialTheme.typography.h5
        )
    }

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.darkText,
        text = digitByLocate(USER_PHONE)
    )

    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Row(
            modifier = Modifier
                .weight(0.49f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.digi_score),
                contentDescription = "",
                modifier = Modifier
                    .size(42.dp)
            )
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.semiMedium)
            ) {
                Row(
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.semiDarkText,
                        text = "${digitByLocate("975")} "
                    )
                    Text(
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.semiDarkText,
                        text = stringResource(R.string.score)
                    )
                }

                Text(
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.darkText,
                    fontWeight = FontWeight.Bold,
                    text = stringResource(id = R.string.digikala_score)
                )
            }
        }

        VerticalDivider(
            modifier = Modifier
                .width(2.dp)
                .height(60.dp),
            color = MaterialTheme.colors.gray.copy(0.2f),
        )

        Column(
            modifier = Modifier
                .clickable {
                    navController.navigate(
                        route = Screen.WebView.route + "?url=${DIGI_WALLET}"
                    )
                }
                .weight(0.49f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.digi_wallet),
                contentDescription = "",
                modifier = Modifier
                    .size(34.dp)
            )

            Text(
                modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.digikala_wallet_active)
            )

        }
    }
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.biggerMedium))
}