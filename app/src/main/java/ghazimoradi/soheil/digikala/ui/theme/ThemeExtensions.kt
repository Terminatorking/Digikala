package ghazimoradi.soheil.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ghazimoradi.soheil.digikala.R

//Shape
val LocalShape = compositionLocalOf { RoundedShape() }

val MaterialTheme.roundedShape: RoundedShape
    @Composable
    @ReadOnlyComposable
    get() = LocalShape.current

//Spacing
val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current

//colors
val isLight: Boolean
    @Composable
    get() = !isSystemInDarkTheme()

val ColorScheme.cyan: Color
    @Composable
    get() = if (isLight) PacificBlue else BatteryChargedBlue

val ColorScheme.gray: Color
    @Composable
    get() = if (isLight) Color.DarkGray else Color.LightGray

val ColorScheme.icon: Color
    @Composable
    get() = if (isLight) Black else White

val ColorScheme.mainBg: Color
    @Composable
    get() = if (isLight) White else ChineseBlack

val ColorScheme.statusBarColor: Color
    @Composable
    get() = if (isLight) BrickRed else Black

val ColorScheme.splashBg: Color
    @Composable
    get() = ImperialRed

val ColorScheme.cursorColor: Color
    @Composable
    get() = TealGreen

val ColorScheme.selectedBottomBar: Color
    @Composable
    get() = if (isLight) OuterSpace else Color.LightGray

val ColorScheme.unSelectedBottomBar: Color
    @Composable
    get() = if (isLight) QuickSilver else DavysGrey

val ColorScheme.bottomBar: Color
    @Composable
    get() = if (isLight) White else DarkCharcoal

val ColorScheme.gold: Color
    @Composable
    get() = SelectiveYellow

val ColorScheme.searchBarBg: Color
    @Composable
    get() = if (isLight) Isabelline else DarkCharcoal

val ColorScheme.darkText: Color
    @Composable
    get() = if (isLight) Arsenic else LightSilver

val ColorScheme.amber: Color
    @Composable
    get() = Amber

val ColorScheme.grayCategory: Color
    @Composable
    get() = Isabelline

val ColorScheme.digiKalaLightRed: Color
    @Composable
    get() = if (isLight) Desire else RedViolet

val ColorScheme.digiKalaLightRedText: Color
    @Composable
    get() = if (isLight) Desire else White

val ColorScheme.digiKalaDarkRed: Color
    @Composable
    get() = Crimson

val ColorScheme.digiKalaRed: Color
    @Composable
    get() = ImperialRed

val ColorScheme.semiDarkText: Color
    @Composable
    get() = if (isLight) GraniteGray else LightSilver

val ColorScheme.settingArrow: Color
    @Composable
    get() = if (isLight) Manatee else LightSilver

val ColorScheme.orange: Color
    @Composable
    get() = GiantsOrange

val ColorScheme.digiKalaLightGreen: Color
    @Composable
    get() = if (isLight) YellowGreen else ArmyGreen

val ColorScheme.green: Color
    @Composable
    get() = if (isLight) KellyGreen else Color.Green

//Typography
val font_medium = FontFamily(
    Font(R.font.iranyekanmedium)
)
val font_bold = FontFamily(
    Font(R.font.iranyekanbold)
)
val font_standard = FontFamily(
    Font(R.font.iranyekan)
)

val Typography.extraBoldNumber: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_bold,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
    )

val Typography.extraSmall: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontSize = 11.sp,
        lineHeight = 25.sp
    )

val Typography.veryExtraSmall: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontSize = 10.sp,
    )

val Typography.body1: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_medium,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 25.sp
    )

val Typography.body2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 25.sp
    )

val Typography.h2: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 25.sp
    )

val Typography.h3: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 25.sp
    )

val Typography.h4: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 25.sp
    )

val Typography.h5: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 25.sp
    )

val Typography.h6: TextStyle
    @Composable
    get() = TextStyle(
        fontFamily = font_standard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 25.sp
    )