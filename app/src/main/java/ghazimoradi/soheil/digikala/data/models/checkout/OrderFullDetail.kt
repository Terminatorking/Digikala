package ghazimoradi.soheil.digikala.data.models.checkout

import ghazimoradi.soheil.digikala.data.models.basket.CartItem

data class OrderFullDetail(
    val token: String,
    val _id: String,
    val userId: String,
    val orderAddress: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderStatus: String,
    val transactionId: String,
    val updatedAt: String,
    val createdAt: String,
    val orderProducts: List<CartItem>

)