package ghazimoradi.soheil.digikala.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Category : Screen("category_screen")
    object Basket : Screen("basket_screen")
    object Profile : Screen("profile_screen")
    data object WebView : Screen("webView_screen")
    data object Settings : Screen("setting_screen")
    data object Checkout : Screen("checkout_screen")
    data object ConfirmPurchase : Screen("confirm_purchase_screen")
    data object ProductDetail : Screen("product_detail_screen")
    data object SubCategoryScreen : Screen("sub_category_screen")
    data object ProductDescription : Screen("product_description_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}