package ghazimoradi.soheil.digikala.data.models.prfile

data class LoginResponse(
    val phone: String,
    val id: String,
    val name: String,
    val role: String,
    val token: String,
)