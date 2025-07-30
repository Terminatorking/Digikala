package ghazimoradi.soheil.digikala.ui.screens.confirmPurchase

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.Constants.isFromPurchase
import ghazimoradi.soheil.digikala.util.DigitHelper

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.mainBg)
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colors.darkText,
                text = DigitHelper.digitByLocateAndSeparator(orderPrice),
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colors.darkText,
                text = "orderState",
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                color = MaterialTheme.colors.darkText,
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colors.darkText,
                text = orderId,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                isFromPurchase = false
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.DigiKalaRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(White)
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.DigiKalaRed,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}