package ghazimoradi.soheil.digikala.data.model.purchase

data class PaymentRequest(
    val merchantId: String,
    val amount: String,
    val callbackUrl: String,
    val description: String
)