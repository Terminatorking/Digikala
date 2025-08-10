package ghazimoradi.soheil.digikala.ui.screens.products.newComment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.data.models.productDetail.NewComment
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.ui.components.Loading
import ghazimoradi.soheil.digikala.ui.components.ProjectTextField
import ghazimoradi.soheil.digikala.ui.theme.Black
import ghazimoradi.soheil.digikala.ui.theme.amber
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.grayCategory
import ghazimoradi.soheil.digikala.ui.theme.h2
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.utils.Constants
import ghazimoradi.soheil.digikala.viewModels.CommentViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommentForm(
    navController: NavController,
    productId: String,
    viewModel: CommentViewModel = hiltViewModel()
) {

    var sliderValue by remember {
        mutableFloatStateOf(0f)
    }

    val score = when (sliderValue.toInt()) {
        2 -> stringResource(id = R.string.very_bad)
        3 -> stringResource(id = R.string.bad)
        4 -> stringResource(id = R.string.normal)
        5 -> stringResource(id = R.string.good)
        6 -> stringResource(id = R.string.very_good)
        else -> ""
    }
    Text(
        text = score,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.medium),
        style = MaterialTheme.typography.h2,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.darkText,
        textAlign = TextAlign.Center
    )

    Slider(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.large),
        value = sliderValue,
        onValueChange = { _sliderValue ->
            sliderValue = _sliderValue
        },
        onValueChangeFinished = {
            Log.d("3636", "sliderValue = $sliderValue")
        },
        valueRange = 1f..6f,
        steps = 4,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.amber,
            activeTrackColor = MaterialTheme.colorScheme.amber,
            inactiveTrackColor = MaterialTheme.colorScheme.grayCategory,
            activeTickColor = MaterialTheme.colorScheme.amber,
            inactiveTickColor = Black
        ),
    )

    HorizontalDivider(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.semiMedium),
        color = MaterialTheme.colorScheme.gray.copy(0.6f),
        thickness = 1.dp,
    )

    var commentTitle by remember { mutableStateOf("") }
    var commentBody by remember { mutableStateOf("") }

    val context = LocalContext.current

    var loading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        viewModel.newCommentResult.collectLatest { newCommentResult ->
            when (newCommentResult) {
                is NetworkResult.Success -> {
                    if (newCommentResult.message.equals("کامنت با موفقیت ثبت شد")) {
                        navController.popBackStack()
                    }
                    loading = false
                }

                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "ProductDetailScreen error : ${newCommentResult.message}")
                }

                is NetworkResult.Loading -> {}
            }
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.medium),
            text = stringResource(id = R.string.say_your_comment),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.darkText,
        )

        Text(
            modifier = Modifier
                .padding(MaterialTheme.spacing.extraSmall),
            text = stringResource(id = R.string.comment_title),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colorScheme.darkText,
        )

        ProjectTextField(
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            value = commentTitle,
        ) {
            commentTitle = it
        }

        Text(
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.biggerMedium,
                    start = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.extraSmall,
                ),
            text = stringResource(id = R.string.comment_text),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colorScheme.darkText,
        )

        ProjectTextField(
            maxLines = 3,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            value = commentBody,
        ) {
            commentBody = it
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MaterialTheme.spacing.small,
                ),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val checkedState = remember { mutableStateOf(false) }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = CheckboxDefaults.colors(MaterialTheme.colorScheme.cyan)
            )
            Text(
                text = stringResource(id = R.string.comment_anonymously),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colorScheme.darkText,
            )
        }

        HorizontalDivider(
            color = MaterialTheme.colorScheme.gray.copy(0.6f),
            thickness = 2.dp
        )

        if (loading) {
            Loading(height = 60.dp)
        } else {
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = MaterialTheme.spacing.medium
                    ),
                onClick = {
                    loading = true

                    val userName =
                        if (Constants.USER_NAME == "null")
                            "کاربر بدون نام"
                        else Constants.USER_NAME.replace(
                            "-",
                            ""
                        )

                    val newComment = NewComment(
                        token = Constants.USER_TOKEN,
                        productId = productId,
                        star = (sliderValue - 1).toInt(),
                        title = commentTitle,
                        description = commentBody,
                        userName = userName
                    )
                    if (newComment.title.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_title_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.star == 0) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_star_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else if (newComment.description.isBlank()) {
                        loading = false
                        Toast.makeText(
                            context,
                            context.getString(R.string.comment_body_null),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        viewModel.setNewComment(newComment)
                    }
                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.extraSmall),

                    text = stringResource(id = R.string.set_new_comment),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4,
                    color = MaterialTheme.colorScheme.darkText
                )
            }
        }
    }
}