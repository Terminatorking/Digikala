package ghazimoradi.soheil.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun DigiKalaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = ImperialRed,
            onPrimary = InterdimensionalBlue,
            secondary = RobinEggBlue
        )
    } else {
        lightColorScheme(
            primary = BrickRed,
            onPrimary = InterdimensionalBlue,
            secondary = RobinEggBlue
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        shapes = Shapes(),
        content = content
    )
}