package ghazimoradi.soheil.digikala.ui.screens.productDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.extraSmall
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h4
import ghazimoradi.soheil.digikala.ui.theme.h5
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun ProductDescriptionSection(
    navController: NavHostController,
    description: String,
    technicalFeatures: String,
) {
    var hasDescription by remember { mutableStateOf(true) }

    if (description.isBlank()) {
        hasDescription = false
    }

    var isTechnicalFeatures by remember { mutableStateOf(true) }

    if (technicalFeatures == "null") {
        isTechnicalFeatures = false
    }

    HorizontalDivider(
        color = MaterialTheme.colorScheme.gray.copy(0.6f),
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium)
    )

    Text(
        modifier = Modifier
            .padding(MaterialTheme.spacing.small),
        text = stringResource(id = R.string.product_desc),
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.darkText,
    )

    if (isTechnicalFeatures) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.gray.copy(0.6f),
            thickness = 1.dp,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
//                    navController.navigate(
//                        Screen.ProductTechnicalFeatures.withArgs(
//                            technicalFeatures
//                        )
//                    )
                }
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.technical_specifications),
                color = MaterialTheme.colorScheme.darkText,
            )
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.icon
            )
        }
    }

    if (hasDescription) {

        HorizontalDivider(
            color = MaterialTheme.colorScheme.gray.copy(0.6f),
            thickness = 1.dp,
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(Screen.ProductDescription.withArgs(description))
                }
                .padding(horizontal = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.product_introduce),
                color = MaterialTheme.colorScheme.darkText,
            )
            Icon(
                Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.icon
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(MaterialTheme.spacing.semiMedium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small),
            text = stringResource(id = R.string.product_desc_feedback),
            style = MaterialTheme.typography.extraSmall,
            color = MaterialTheme.colorScheme.darkText,
        )

        Icon(
            tint = MaterialTheme.colorScheme.icon,
            painter = painterResource(id = R.drawable.info),
            modifier = Modifier.size(20.dp), contentDescription = ""
        )
    }
}