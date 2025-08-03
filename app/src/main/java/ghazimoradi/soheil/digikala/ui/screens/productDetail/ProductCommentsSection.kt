package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.model.productDetail.Comment
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.util.DigitHelper.digitByLocate

@Composable
fun ProductCommentsSection(
    navController: NavHostController,
    productId: String,
    comments: List<Comment>,
    commentCount: String
) {

    HorizontalDivider(
        color = MaterialTheme.colorScheme.gray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.semiLarge),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.user_comments),
                color = MaterialTheme.colorScheme.darkText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
            )
            Text(
                text = "${digitByLocate(commentCount)} " + stringResource(R.string.comment),
                color = MaterialTheme.colorScheme.cyan,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .background(Transparent)
                    .clickable {
//                    navController.navigate(
//                        Screen.AllComment.withArgs(
//                            productId,
//                            commentCount,
//                            PRODUCT_COMMENTS
//                        )
//                    )
                    }
            )
        }
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.medium),
    ) {
        items(comments) { comment ->
            TextCommentCard(comment)
        }
        item {
            CommentShowMoreItem(navController, productId, commentCount)
        }
    }
}