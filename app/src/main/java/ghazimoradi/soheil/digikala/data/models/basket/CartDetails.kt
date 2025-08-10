package ghazimoradi.soheil.digikala.data.models.basket

data class CartDetails(
    val totalCount : Int,
    val totalPrice: Long,
    val totalDiscount: Long,
    val payablePrice: Long
)