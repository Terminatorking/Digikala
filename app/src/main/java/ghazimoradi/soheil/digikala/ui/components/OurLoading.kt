package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.mainBg

@Composable
fun OurLoading(height: Dp = 200.dp, isDark: Boolean = !isSystemInDarkTheme()) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.mainBg)
            .fillMaxWidth()
            .height(height),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Loading3Dots(isDark = isDark)
    }
}