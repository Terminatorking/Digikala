package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import ghazimoradi.soheil.digikala.ui.theme.CursorColor
import ghazimoradi.soheil.digikala.ui.theme.DigiKalaRed
import ghazimoradi.soheil.digikala.ui.theme.Transparent
import ghazimoradi.soheil.digikala.ui.theme.cyan
import ghazimoradi.soheil.digikala.ui.theme.darkText
import ghazimoradi.soheil.digikala.ui.theme.gray
import ghazimoradi.soheil.digikala.ui.theme.h6
import ghazimoradi.soheil.digikala.ui.theme.roundedShape
import ghazimoradi.soheil.digikala.ui.theme.searchBarBg

@Composable
fun ProjectTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "",
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    onValueChange: (textFieldText: String) -> Unit
) {

    TextField(
        maxLines = maxLines,
        singleLine = singleLine,
        value = value,
        onValueChange = { textFieldText ->
            onValueChange(textFieldText)
        },
        modifier = modifier,
        shape = MaterialTheme.roundedShape.small,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.darkText,
            focusedTextColor = MaterialTheme.colorScheme.darkText,
            errorIndicatorColor = MaterialTheme.colorScheme.DigiKalaRed,
            focusedIndicatorColor = MaterialTheme.colorScheme.cyan,
            unfocusedIndicatorColor = Transparent,
            disabledIndicatorColor = Transparent,
            focusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            unfocusedContainerColor = MaterialTheme.colorScheme.searchBarBg,
            cursorColor = MaterialTheme.colorScheme.CursorColor,
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = placeholder,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.gray,
                fontWeight = FontWeight.Medium,
            )
        }
    )
}