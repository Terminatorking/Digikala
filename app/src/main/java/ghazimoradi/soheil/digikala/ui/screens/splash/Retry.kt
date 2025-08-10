package ghazimoradi.soheil.digikala.ui.screens.splash

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.White
import ghazimoradi.soheil.digikala.ui.theme.h6

@Composable
fun Retry(onRetryClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onRetryClick)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.h6,
            text = stringResource(R.string.check_net),
            color = White
        )
        Icon(
            Icons.Filled.Refresh,
            tint = White,
            contentDescription = "",
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}