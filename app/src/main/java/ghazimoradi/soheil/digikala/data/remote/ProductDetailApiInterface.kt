package ghazimoradi.soheil.digikala.data.remote

import ghazimoradi.soheil.digikala.data.model.ResponseResult
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.model.productDetail.ProductDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductDetailApiInterface {

    @GET("v1/getProductById")
    suspend fun getProductById(
        @Query("id") productId: String
    ): Response<ResponseResult<ProductDetail>>

    @GET("v1/getSimilarProducts")
    suspend fun getSimilarProducts(
        @Query("categoryId") categoryId: String
    ): Response<ResponseResult<List<StoreProduct>>>
}