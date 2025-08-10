package ghazimoradi.soheil.digikala.ui.screens.confirmPurchase

import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.checkout.ConfirmPurchase
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentRequest
import ghazimoradi.soheil.digikala.data.models.purchase.PaymentVerificationRequest
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.mainBg
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.Constants.USER_TOKEN
import ghazimoradi.soheil.digikala.utils.Constants.ZARINPAL_MERCHANT_ID
import ghazimoradi.soheil.digikala.utils.Constants.ZARINPAL_PAYMENT_URL
import ghazimoradi.soheil.digikala.utils.Constants.afterPurchaseUrl
import ghazimoradi.soheil.digikala.utils.Constants.isFromPurchase
import ghazimoradi.soheil.digikala.utils.Constants.purchaseOrderId
import ghazimoradi.soheil.digikala.utils.Constants.purchasePrice
import ghazimoradi.soheil.digikala.utils.DigitHelper
import ghazimoradi.soheil.digikala.viewModels.BasketViewModel
import ghazimoradi.soheil.digikala.viewModels.CheckoutViewModel
import ghazimoradi.soheil.digikala.viewModels.PurchaseViewModel

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String,
    purchaseViewModel: PurchaseViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {

    purchaseOrderId = orderId
    purchasePrice = orderPrice

    val context = LocalContext.current

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    val purchaseResult by purchaseViewModel.purchaseResult.collectAsState(null)
    val verifyPurchaseResult by purchaseViewModel.verifyPurchaseResult.collectAsState(null)

    LaunchedEffect(true) {
        if (isFromPurchase) {
            purchaseViewModel.verifyPurchase(
                PaymentVerificationRequest(
                    merchant_id = ZARINPAL_MERCHANT_ID,
                    authority = afterPurchaseUrl.toUri().getQueryParameter("Authority")
                        .toString(),
                    amount = orderPrice + "0",
                )
            )
        } else {
            purchaseViewModel.startPurchase(
                PaymentRequest(
                    merchant_id = ZARINPAL_MERCHANT_ID,
                    amount = orderPrice + "0",
                    callback_url = "truelearn://digikala",
                    description = "خرید تستی از دیجی کالا",
                )
            )
        }
    }

    if (purchaseResult != null) {
        purchaseViewModel.openBrowser(
            context = context,
            url = ZARINPAL_PAYMENT_URL + purchaseResult!!.data.authority
        )
    }

    if (verifyPurchaseResult != null) {
        if (verifyPurchaseResult!!.data.message == "Paid") {
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllItems()
            checkoutViewModel.confirmPurchase(
                ConfirmPurchase(
                    token = USER_TOKEN,
                    transactionId = verifyPurchaseResult!!.data.ref_id.toString(),
                    orderId = orderId
                )
            )
            Log.e("3636", "Transaction ID : ${verifyPurchaseResult!!.data.ref_id}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg)
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
                color = MaterialTheme.colorScheme.darkText,
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colorScheme.darkText,
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
                color = MaterialTheme.colorScheme.darkText,
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colorScheme.darkText,
                text = orderState,
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
                color = MaterialTheme.colorScheme.darkText,
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.h5
            )

            Text(
                color = MaterialTheme.colorScheme.darkText,
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
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.DigiKalaRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(White)
        ) {
            Text(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colorScheme.DigiKalaRed,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}