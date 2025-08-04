package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.util.Constants.ENGLISH_LANG
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE

@Composable
fun IconWithRotate(
    imageVector: ImageVector,
    tint: Color = MaterialTheme.colorScheme.icon,
    onClick: () -> Unit = {}
) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer(rotationZ = 180f)
                .clickable { onClick() }
        )
    } else {
        Icon(
            modifier = Modifier.clickable { onClick() },
            imageVector = imageVector,
            contentDescription = "",
            tint = tint,
        )
    }
}

@Composable
fun IconWithRotate(
    painter: Painter,
    tint: Color,
    width: Dp = 40.dp,
    height: Dp = 40.dp,
    onClick: () -> Unit = {}
) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer(rotationZ = 180f)
                .size(width, height)
                .clickable { onClick() }
        )
    } else {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .size(width, height)
                .clickable { onClick() }
        )
    }
}