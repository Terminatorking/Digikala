package ghazimoradi.soheil.digikala.data.models

data class ResponseResult<T>(
    val message : String ,
    val data : T ,
    val success: Boolean
)