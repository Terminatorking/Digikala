package ghazimoradi.soheil.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ImperialRed = Color(0xFFed1b34)
val BrickRed = Color(0xFFCE3C4D)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val White = Color.White
val Black = Color.Black
private val LightGray = Color.LightGray
private val DarkGray = Color.DarkGray
val Transparent = Color.Transparent
val Red = Color.Red

val ColorScheme.cyan: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF0fabc6) else Color(0xFF17BFD3)

val ColorScheme.gray: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) DarkGray else LightGray

val ColorScheme.icon: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Black else White

val ColorScheme.mainBg: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) White else Black

val ColorScheme.splashBg: Color
    @Composable
    get() = ImperialRed

val ColorScheme.CursorColor: Color
    @Composable
    get() = Color(0xFF018577)

val ColorScheme.selectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF43474C) else Color(0xFFCFD4DA)

val ColorScheme.unSelectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFFA4A1A1) else Color(0xFF575A5E)

val ColorScheme.bottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) White else Color(0xFF303235)

val ColorScheme.Gold: Color
    @Composable
    get() = Color(0xFFf9bc01)

val ColorScheme.grayAlpha: Color
    @Composable
    get() = Color(0xFFc1c2c6)

val ColorScheme.searchBarBg: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFFF1F0EE) else Color(0xFF303235)

val ColorScheme.darkText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF414244) else Color(0xFFD8D8D8)

val ColorScheme.amber: Color
    @Composable
    get() = Color(0xffFFBF00)

val ColorScheme.grayCategory: Color
    @Composable
    get() = Color(0xFFF1F0EE)

val ColorScheme.DigiKalaLightRed: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xffef4056) else Color(0xFF8D2633)

val ColorScheme.DigiKalaLightRedText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xffef4056) else White

val ColorScheme.DigiKalaDarkRed: Color
    @Composable
    get() = Color(0xFFe6123d)

val ColorScheme.DigiKalaRed: Color
    @Composable
    get() = ImperialRed

val ColorScheme.semiDarkText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF5C5E61) else Color(0xFFD8D8D8)

val ColorScheme.settingArrow: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFF9E9FB1) else Color(0xFFD8D8D8)

val ColorScheme.Oranges: Color
    @Composable
    get() = Color(0xFFFF5722)

val ColorScheme.DigikalaLightGreen: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xff86bf3c) else Color(0xFF3A531A)

val ColorScheme.Green: Color
    @Composable
    get() = Color(0xFF00A049)