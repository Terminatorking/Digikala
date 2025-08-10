package ghazimoradi.soheil.digikala.ui.screens.products.productDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ghazimoradi.soheil.digikala.data.models.productDetail.ProductColor
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.h4

val selectedColorItem: MutableState<ProductColor?> = mutableStateOf(null)

@Composable
fun ProductSelectColorSection(
    colors: List<ProductColor>,
    onSelectionChanged: (String) -> Unit = { colorName ->
        colors.forEach {
            if (it.color == colorName) {
                selectedColorItem.value = it
            }
        }
    },
) {

    Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {

        Text(
            text = stringResource(R.string.color) +
                    " ${selectedColorItem.value?.color ?: stringResource(R.string.not_selected)}",

            color = MaterialTheme.colorScheme.darkText,
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        var colorState by remember {
            mutableStateOf<List<ProductColor>>(emptyList())
        }
        colorState = colors

        LazyRow {
            items(colorState) { productColor ->
                ColorChipItem(
                    productColor,
                    isSelected = selectedColorItem.value?.color == productColor.color
                ) {
                    onSelectionChanged(it)
                }
            }
        }
    }
}