package ghazimoradi.soheil.digikala.ui.screens.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import ghazimoradi.soheil.digikala.ui.components.OurLoading

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebPageScreen(
    navController: NavController,
    url: String
) {
    var isLoading by remember { mutableStateOf(true) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                webViewClient = object : WebViewClient() {

                    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                        super.onPageStarted(view, url, favicon)
                        isLoading = true
                    }

                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        isLoading = false
                    }
                }

                settings.javaScriptEnabled = true
                settings.userAgentString = System.getProperty("http.agent")
                loadUrl(url)
            }
        },
        update = { webView ->
            webView.loadUrl(url)
        },
    )

    if (isLoading) {
        OurLoading(LocalWindowInfo.current.containerSize.height.dp, true)
    }
}