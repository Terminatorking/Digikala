package ghazimoradi.soheil.digikala.data.models.home

data class AmazingItem(
    val _id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
)