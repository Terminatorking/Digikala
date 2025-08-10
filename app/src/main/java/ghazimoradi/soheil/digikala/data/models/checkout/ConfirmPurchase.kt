package ghazimoradi.soheil.digikala.data.models.checkout

data class ConfirmPurchase(
    val orderId: String,
    val token: String,
    val transactionId: String
)