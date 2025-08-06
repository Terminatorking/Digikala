package ghazimoradi.soheil.digikala.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Home : Screen("home_screen")
    object Category : Screen("category_screen")
    object Basket : Screen("basket_screen")
    object Profile : Screen("profile_screen")
    object WebView : Screen("webView_screen")
    object Settings : Screen("setting_screen")
    object Checkout : Screen("checkout_screen")
    object ConfirmPurchase : Screen("confirm_purchase_screen")
    object ProductDetail : Screen("product_detail_screen")
    object SubCategoryScreen : Screen("sub_category_screen")
    object ProductDescription : Screen("product_description_screen")
    object ProductTechnicalFeatures : Screen("product_technical_features_screen")
    object AllComment : Screen("all_comment_screen")
    object NewComment : Screen("new_comment_screen")
    object ProductPriceChart : Screen("product_priceChart_screen")
    object ShowAddress : Screen("show_address_screen")
    object AddAddress : Screen("add_address_screen")

    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}