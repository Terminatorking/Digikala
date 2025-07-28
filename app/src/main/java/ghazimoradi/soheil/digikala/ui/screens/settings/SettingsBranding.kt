package ghazimoradi.soheil.digikala.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.BuildConfig
import ghazimoradi.soheil.digikala.R
import ghazimoradi.soheil.digikala.ui.theme.semiDarkText
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun SettingsBranding() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(100.dp),
            painter = painterResource(id = R.drawable.digi_red_english),
            contentDescription = ""
        )
        Text(
            text = stringResource(id = R.string.version_app, BuildConfig.VERSION_NAME),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.semiDarkText
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.truelearn_technical_team),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.semiDarkText
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraSmall))
            Image(
                modifier = Modifier.height(24.dp),
                painter = painterResource(id = R.drawable.truelearn_icon),
                contentDescription = ""
            )
        }
    }
}