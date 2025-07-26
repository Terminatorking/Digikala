package ghazimoradi.soheil.digikala.ui.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun getScreenHeight(): Dp = LocalConfiguration.current.screenHeightDp.dp