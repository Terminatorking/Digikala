package ghazimoradi.soheil.digikala.ui.screens.category.categoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import ghazimoradi.soheil.digikala.data.models.category.Sub
import ghazimoradi.soheil.digikala.ui.theme.spacing
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.h3
import ghazimoradi.soheil.digikala.ui.theme.h5

@Composable
fun CategoryItem(
    navController: NavHostController,
    categoryId: String,
    title: String,
    subList: List<Sub>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            color = MaterialTheme.colorScheme.darkText,
            text = title,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .clickable {
                      navController.navigate(Screen.SubCategoryScreen.withArgs(categoryId))
                },
            text = stringResource(id = R.string.see_all),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colorScheme.cyan
        )
    }

    LazyRow {
        items(subList) { list ->
            SubCategoryItem(navController, list)
        }
    }
}