package ghazimoradi.soheil.digikala.data.model.purchase

data class PaymentResponse(
    val data: Data,
    val errors: List<Any>
)

data class Data(
    val authority: String,
    val fee: Int,
    val feeType: String,
    val code: Int,
    val message: String
)