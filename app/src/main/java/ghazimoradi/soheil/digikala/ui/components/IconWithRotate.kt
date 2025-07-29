package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ghazimoradi.soheil.digikala.ui.theme.icon
import ghazimoradi.soheil.digikala.util.Constants.ENGLISH_LANG
import ghazimoradi.soheil.digikala.util.Constants.USER_LANGUAGE

@Composable
fun IconWithRotate(imageVector: ImageVector, onClick: () -> Unit = {}) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            tint = MaterialTheme.colors.icon,
            modifier = Modifier
                .graphicsLayer(rotationZ = 180f)
                .clickable { onClick.invoke() }
        )
    } else {
        Icon(
            modifier = Modifier.clickable { onClick.invoke() },
            imageVector = imageVector,
            contentDescription = "",
            tint = MaterialTheme.colors.icon
        )
    }
}

@Composable
fun IconWithRotate(painter: Painter, tint: Color) {

    if (USER_LANGUAGE == ENGLISH_LANG) {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .graphicsLayer(rotationZ = 180f)
                .size(40.dp, 40.dp)
        )
    } else {
        Icon(
            painter = painter,
            contentDescription = "",
            tint = tint,
            modifier = Modifier.size(40.dp, 40.dp)
        )
    }
}