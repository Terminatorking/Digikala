package ghazimoradi.soheil.digikala.data.model.purchase

data class PaymentVerificationResponse(
    val data: PaymentVerificationData,
    val errors: List<Any>
)

data class PaymentVerificationData(
    val wages: List<Any>,
    val code: Int,
    val message: String,
    val cardHash: String,
    val cardPan: String,
    val refId: Long,
    val feeType: String,
    val fee: Int,
    val shaparakFee: String,
    val orderId: String
)