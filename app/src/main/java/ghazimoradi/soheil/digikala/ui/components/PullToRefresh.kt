package ghazimoradi.soheil.digikala.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefresh(
    pullToRefreshState: PullToRefreshState = rememberPullToRefreshState(),
    isRefreshing: Boolean = false,
    onRefresh: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        state = pullToRefreshState,
    ) {
        content()
    }
}