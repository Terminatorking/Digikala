package ghazimoradi.soheil.digikala.ui.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.LocalShape
import ghazimoradi.soheil.digikala.ui.theme.LocalSpacing
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg

@Composable
fun SearchBarSection() {

    Card(
        colors = CardDefaults.cardColors(Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalSpacing.current.small)
                .clip(LocalShape.current.biggerSmall)
                .background(MaterialTheme.colorScheme.searchBarBg)
        ) {
            SearchContent()
        }
    }
}