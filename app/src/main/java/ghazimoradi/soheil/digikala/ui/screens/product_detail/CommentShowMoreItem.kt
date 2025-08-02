package ghazimoradi.soheil.digikala.ui.screens.product_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun CommentShowMoreItem(
    navController: NavHostController,
    productId: String,
    commentCount: String,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(180.dp)
            .height(240.dp)
            .padding(vertical = MaterialTheme.spacing.medium)
            .clickable {
//                navController.navigate(
//                    Screen.AllComment.withArgs(
//                        productId,
//                        commentCount,
//                        PRODUCT_COMMENTS
//                    )
//                )
            }

    ) {
        Icon(
            painter = painterResource(id = R.drawable.show_more),
            contentDescription = "",
            tint = MaterialTheme.colors.cyan,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(R.string.see_all),
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
            textAlign = TextAlign.Center,
        )
    }
}