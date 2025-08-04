package ghazimoradi.soheil.digikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.cyan: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) PacificBlue else BatteryChargedBlue

val ColorScheme.gray: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.DarkGray else Color.LightGray

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
    get() = TealGreen

val ColorScheme.selectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) OuterSpace else Color.LightGray

val ColorScheme.unSelectedBottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) QuickSilver else DavysGrey

val ColorScheme.bottomBar: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) White else DarkCharcoal

val ColorScheme.Gold: Color
    @Composable
    get() = SelectiveYellow

val ColorScheme.searchBarBg: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Isabelline else DarkCharcoal

val ColorScheme.darkText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Arsenic else LightSilver

val ColorScheme.amber: Color
    @Composable
    get() = Amber

val ColorScheme.grayCategory: Color
    @Composable
    get() = Isabelline

val ColorScheme.DigiKalaLightRed: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Desire else RedViolet

val ColorScheme.DigiKalaLightRedText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Desire else White

val ColorScheme.DigiKalaDarkRed: Color
    @Composable
    get() = Crimson

val ColorScheme.DigiKalaRed: Color
    @Composable
    get() = ImperialRed

val ColorScheme.semiDarkText: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) GraniteGray else LightSilver

val ColorScheme.settingArrow: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Manatee else LightSilver

val ColorScheme.Oranges: Color
    @Composable
    get() = GiantsOrange

val ColorScheme.DigiKalaLightGreen: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) YellowGreen else ArmyGreen

val ColorScheme.Green: Color
    @Composable
    get() = Color.Green