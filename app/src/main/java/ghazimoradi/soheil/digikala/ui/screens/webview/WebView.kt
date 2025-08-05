package ghazimoradi.soheil.digikala.ui.screens.webview

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
import androidx.compose.ui.viewinterop.AndroidView
import ghazimoradi.soheil.digikala.ui.components.Loading
import ghazimoradi.soheil.digikala.ui.components.getScreenHeight

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebView(url: String) {
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
        Loading(getScreenHeight())
    }
}