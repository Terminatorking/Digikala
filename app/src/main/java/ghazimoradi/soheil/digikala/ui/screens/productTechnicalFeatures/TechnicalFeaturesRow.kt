package ghazimoradi.soheil.digikala.ui.screens.productTechnicalFeatures

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun TechnicalFeaturesRow(
    key: String,
    value: String
) {

    Row {
        Column(
            modifier = Modifier
                .weight(0.35f)
                .padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.medium
                )
        ) {
            Text(
                text = key,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.darkText,
            )
        }

        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.medium
                )
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.darkText
            )
            Spacer(
                modifier = Modifier
                    .padding(top = MaterialTheme.spacing.biggerSmall)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(MaterialTheme.colorScheme.gray)
            )
        }
    }
}