package ghazimoradi.soheil.digikala.ui.components.extentions

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import ghazimoradi.soheil.digikala.utils.Constants.ENGLISH_LANG
import ghazimoradi.soheil.digikala.utils.Constants.USER_LANGUAGE

@Composable
fun logoChangeByLanguage(
    @DrawableRes enLogo: Int,
    @DrawableRes faLogo: Int,
): Painter {
    return if (USER_LANGUAGE == ENGLISH_LANG) {
        painterResource(enLogo)
    } else {
        painterResource(faLogo)
    }
}