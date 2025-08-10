package ghazimoradi.soheil.digikala.data.models.purchase

data class PaymentVerificationRequest(
    val merchant_id: String,
    val authority: String,
    val amount: String
)