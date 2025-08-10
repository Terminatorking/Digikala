package ghazimoradi.soheil.digikala.ui.screens.basket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.icon.IconWithRotate
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun LoginOrRegisterSection(navController: NavController) {

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.searchBarBg),
        modifier = Modifier
            .padding(MaterialTheme.spacing.medium)
            .clickable { navController.navigate(Screen.Profile.route) },
        shape = MaterialTheme.roundedShape.small,
        elevation = CardDefaults.cardElevation(MaterialTheme.elevation.extraSmall),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            IconWithRotate(
                painter = painterResource(id = R.drawable.import_96_orenge),
                tint = MaterialTheme.colorScheme.amber,
            )

            Spacer(modifier = Modifier.weight(0.05f))

            Column(
                modifier = Modifier.weight(0.8f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.darkText,
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_or_register_msg),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.darkText,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.h6,
                )
            }

            Spacer(modifier = Modifier.weight(0.05f))

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.icon,
                modifier = Modifier
                    .size(25.dp)
                    .weight(0.1f)
                    .align(Alignment.Top)
                    .padding(top = MaterialTheme.spacing.small)
            )
        }
    }
}