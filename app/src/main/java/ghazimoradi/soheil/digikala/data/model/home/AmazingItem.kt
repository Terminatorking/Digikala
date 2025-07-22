package ghazimoradi.soheil.digikala.data.model.home

data class AmazingItem(
    val id: String,
    val name: String,
    val seller: String,
    val price: Long,
    val discountPercent: Int,
    val image: String,
)