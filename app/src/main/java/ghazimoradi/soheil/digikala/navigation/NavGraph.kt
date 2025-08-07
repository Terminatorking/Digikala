package ghazimoradi.soheil.digikala.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ghazimoradi.soheil.digikala.ui.screens.addAddress.AddAddressScreen
import ghazimoradi.soheil.digikala.ui.screens.basket.BasketScreen
import ghazimoradi.soheil.digikala.ui.screens.category.CategoryScreen
import ghazimoradi.soheil.digikala.ui.screens.subCategory.SubCategoryScreen
import ghazimoradi.soheil.digikala.ui.screens.checkout.CheckoutScreen
import ghazimoradi.soheil.digikala.ui.screens.confirmPurchase.ConfirmPurchaseScreen
import ghazimoradi.soheil.digikala.ui.screens.home.HomeScreen
import ghazimoradi.soheil.digikala.ui.screens.webview.WebView
import ghazimoradi.soheil.digikala.ui.screens.products.productTechnicalFeatures.ProductTechnicalFeaturesScreen
import ghazimoradi.soheil.digikala.ui.screens.products.newComment.NewCommentScreen
import ghazimoradi.soheil.digikala.ui.screens.products.productDescription.ProductDescriptionScreen
import ghazimoradi.soheil.digikala.ui.screens.products.productDetail.ProductDetailScreen
import ghazimoradi.soheil.digikala.ui.screens.products.allProductComments.AllProductCommentsScreen
import ghazimoradi.soheil.digikala.ui.screens.products.productPriceChart.ProductPriceChartScreen
import ghazimoradi.soheil.digikala.ui.screens.profile.ProfileScreen
import ghazimoradi.soheil.digikala.ui.screens.settings.SettingsScreen
import ghazimoradi.soheil.digikala.ui.screens.showAddress.ShowAddressScreen
import ghazimoradi.soheil.digikala.ui.screens.splash.SplashScreen
import ghazimoradi.soheil.digikala.ui.screens.userAccountInfo.UserAccountInfoScreen
import ghazimoradi.soheil.digikala.ui.screens.userFavoriteProducts.UserFavoriteProductsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(navController = navController)
        }

        composable(route = Screen.Basket.route) {
            BasketScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(route = Screen.Checkout.route) {
            CheckoutScreen(navController = navController)
        }

        composable(route = Screen.AddAddress.route) {
            AddAddressScreen(navController = navController)
        }

        composable(route = Screen.UserAccountInfo.route) {
            UserAccountInfoScreen(navController = navController)
        }

        composable(route = Screen.UserFavoriteProducts.route) {
            UserFavoriteProductsScreen(navController = navController)
        }

        composable(
            route = Screen.ConfirmPurchase.route + "/{orderId}/{orderPrice}",
            arguments = listOf(
                navArgument("orderId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("orderPrice") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("orderId")?.let { orderId ->
                it.arguments!!.getString("orderPrice")?.let { orderPrice ->
                    ConfirmPurchaseScreen(
                        navController = navController,
                        orderId = orderId,
                        orderPrice = orderPrice
                    )
                }
            }
        }

        composable(
            route = Screen.ProductDetail.route + "/{productId}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                ProductDetailScreen(
                    navController = navController,
                    productId = productId
                )
            }
        }

        composable(
            route = Screen.SubCategoryScreen.route + "/{categoryId}",
            arguments = listOf(
                navArgument("categoryId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("categoryId")?.let { categoryId ->
                SubCategoryScreen(
                    navController = navController,
                    categoryId = categoryId
                )
            }
        }
        composable(
            route = Screen.ProductDescription.route + "/{description}",
            arguments = listOf(
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("description")?.let { description ->
                ProductDescriptionScreen(
                    navController = navController,
                    description = description
                )
            }
        }
        composable(
            route = Screen.ProductTechnicalFeatures.route + "/{jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductTechnicalFeaturesScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }
        }

        composable(
            route = Screen.AllComment.route + "/{productId}/{commentsCount}/{pageName}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("commentsCount") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("pageName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("commentsCount")?.let { commentsCount ->
                    it.arguments!!.getString("pageName")?.let { pageName ->
                        AllProductCommentsScreen(
                            navController = navController,
                            productId = productId,
                            commentsCount = commentsCount,
                            pageName = pageName
                        )
                    }
                }
            }
        }

        composable(
            route = Screen.NewComment.route +
                    "?productId={productId}?productName={productName}?imageUrl={imageUrl}",
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("productName") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                },
                navArgument("imageUrl") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("productId")?.let { productId ->
                it.arguments!!.getString("productName")?.let { productName ->
                    it.arguments!!.getString("imageUrl")?.let { imageUrl ->
                        NewCommentScreen(
                            navController = navController,
                            productId = productId,
                            productName = productName,
                            imageUrl = imageUrl
                        )
                    }
                }
            }
        }

        composable(route = Screen.ProductPriceChart.route + "?jsonString={jsonString}",
            arguments = listOf(
                navArgument("jsonString") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("jsonString")?.let { jsonString ->
                ProductPriceChartScreen(
                    navController = navController,
                    jsonString = jsonString
                )
            }

        }

        composable(route = Screen.ShowAddress.route + "/{isFromBasket}",
            arguments = listOf(
                navArgument("isFromBasket") {
                    type = NavType.StringType
                    defaultValue = " "
                    nullable = true
                }
            )
        ) {
            it.arguments!!.getString("isFromBasket")?.let { isFromBasket ->
                ShowAddressScreen(
                    navController = navController,
                    isFromBasket = isFromBasket.toInt()
                )
            }
        }

        composable(
            route = Screen.WebView.route + "?url={url}",
            arguments = listOf(
                navArgument("url") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("url")?.let {
                WebView(url = it)
            }
        }
    }
}