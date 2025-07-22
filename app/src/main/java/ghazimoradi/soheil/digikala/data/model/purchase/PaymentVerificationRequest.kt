package ghazimoradi.soheil.digikala.data.model.purchase

data class PaymentVerificationRequest(
    val merchantId: String,
    val authority: String,
    val amount: String
)