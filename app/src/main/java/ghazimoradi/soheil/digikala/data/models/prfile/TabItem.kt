package ghazimoradi.soheil.digikala.data.models.prfile

import androidx.compose.runtime.Composable

data class TabItem(
    val title : String,
    val screen : @Composable () -> Unit
)