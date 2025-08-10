package ghazimoradi.soheil.digikala.repositories

import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import ghazimoradi.soheil.digikala.data.models.productDetail.ProductDetail
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.ProductDetailApiInterface
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