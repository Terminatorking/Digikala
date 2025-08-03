package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.data.model.productDetail.ProductColor
import ghazimoradi.soheil.digikala.ui.theme.CursorColor
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun ColorChipItem(
    item: ProductColor,
    isSelected: Boolean,
    onSelectionChanged: (String) -> Unit = {},
) {
    Surface(
        modifier =
            if (isSelected)
                Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .border(width = 1.dp, MaterialTheme.colors.CursorColor, CircleShape)
            else Modifier
                .padding(MaterialTheme.spacing.extraSmall)
                .border(width = 1.dp, Transparent, CircleShape),

        shape = MaterialTheme.roundedShape.biggerMedium,
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colors.searchBarBg)
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(item.color)
                    }
                )
                .padding(MaterialTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .size(20.dp)
                    .border(1.dp, Color.Gray, CircleShape),
                onDraw = {
                    drawCircle(Color(("ff" + item.code.removePrefix("#").lowercase()).toLong(16)))
                }
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

            Text(
                color = MaterialTheme.colors.darkText,
                text = item.color,
                style = MaterialTheme.typography.h6
            )
        }
    }
}