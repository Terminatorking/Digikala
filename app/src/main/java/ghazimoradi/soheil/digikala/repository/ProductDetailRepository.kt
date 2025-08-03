package ghazimoradi.soheil.digikala.repository

import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.model.productDetail.ProductDetail
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.data.remote.ProductDetailApiInterface
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(
    private val api: ProductDetailApiInterface
) : BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }
}