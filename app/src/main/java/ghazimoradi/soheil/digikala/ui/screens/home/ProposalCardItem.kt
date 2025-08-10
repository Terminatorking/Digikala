package ghazimoradi.soheil.digikala.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import ghazimoradi.soheil.digikala.data.models.home.Slider
import ghazimoradi.soheil.digikala.navigation.Screen
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun ProposalCardItem(slider: Slider, navController: NavController) {
    Card(
        shape = MaterialTheme.roundedShape.semiMedium,
        modifier = Modifier
            .clickable {
                navController.navigate(
                    Screen.WebView.route + "?url=${slider.url}"
                )
            }
            .fillMaxWidth(0.5f)
            .height(140.dp)
            .padding(
                horizontal = MaterialTheme.spacing.small,
                vertical = MaterialTheme.spacing.small
            ),
    ) {
        Image(
            painter = rememberAsyncImagePainter(slider.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
}