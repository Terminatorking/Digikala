package ghazimoradi.soheil.digikala.ui.screens.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.CursorColor
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg
import ghazimoradi.soheil.digikala.ui.theme.spacing

@Composable
fun LoginEditText(
    value: String,
    placeholder: String,
    onValueChange: (it: String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .padding(
                start = MaterialTheme.spacing.semiLarge,
                end = MaterialTheme.spacing.semiLarge,
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.semiLarge
            ),
        shape = MaterialTheme.roundedShape.small,
        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Transparent,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            focusedContainerColor = MaterialTheme.colors.searchBarBg,
            unfocusedContainerColor = MaterialTheme.colors.searchBarBg,
            cursorColor = MaterialTheme.colors.CursorColor,
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = placeholder,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.gray,
                fontWeight = FontWeight.Medium,
            )
        }
    )
}