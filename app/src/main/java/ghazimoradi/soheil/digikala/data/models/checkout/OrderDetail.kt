package ghazimoradi.soheil.digikala.data.models.checkout

import ghazimoradi.soheil.digikala.data.models.basket.CartItem

data class OrderDetail(
    val token: String,
    val orderAddress: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderProducts : List<CartItem>
)