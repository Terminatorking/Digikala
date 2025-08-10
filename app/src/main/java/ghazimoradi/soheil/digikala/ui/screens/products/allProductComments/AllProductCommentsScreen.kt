package ghazimoradi.soheil.digikala.ui.screens.products.allProductComments

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.components.loading.Loading
import ghazimoradi.soheil.digikala.ui.components.extentions.getScreenHeight
import ghazimoradi.soheil.digikala.ui.theme.*
import ghazimoradi.soheil.digikala.utils.Constants.PRODUCT_COMMENTS
import ghazimoradi.soheil.digikala.utils.DigitHelper
import ghazimoradi.soheil.digikala.viewModels.CommentViewModel

@Composable
fun AllProductCommentsScreen(
    viewModel: CommentViewModel = hiltViewModel(),
    navController: NavHostController,
    productId: String,
    commentsCount: String,
    pageName: String
) {
    val context = LocalContext.current

    val commentsCountText = if (commentsCount != "1")
        "${DigitHelper.digitByLocate(commentsCount)} ${stringResource(id = R.string.comment)}"
    else context.getString(R.string.all_comments)

    if (pageName == PRODUCT_COMMENTS) viewModel.getCommentList(productId)
    else viewModel.getUserComments()

    val commentsList =
        if (pageName == PRODUCT_COMMENTS) viewModel.productCommentsList.collectAsLazyPagingItems()
        else viewModel.UserCommentsList.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.mainBg)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = MaterialTheme.colorScheme.icon,
                imageVector = Icons.Default.Close,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.medium)
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(24.dp)
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = commentsCountText,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText,
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(3.dp)
                .background(MaterialTheme.colorScheme.searchBarBg)
        )

        Box(modifier = Modifier.fillMaxSize()) {

            LazyColumn(Modifier.fillMaxSize()) {
                items(
                    count = commentsList.itemCount,
                    key = commentsList.itemKey { comment -> comment._id },
                    contentType = commentsList.itemContentType { "Comments" }
                ) { index ->
                    CommentsItem(commentsList[index]!!)
                }

                commentsList.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
                                Loading(getScreenHeight())
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            item {
                                Loading(30.dp)
                            }
                        }

                        loadState.append is LoadState.Error -> {}
                    }
                }
            }
        }
    }
}