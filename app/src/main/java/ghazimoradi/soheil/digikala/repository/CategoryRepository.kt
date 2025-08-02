package ghazimoradi.soheil.digikala.repository

import ghazimoradi.soheil.digikala.data.model.category.SubCategory
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.CategoryApiInterface
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val api: CategoryApiInterface
) : BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }

    suspend fun getProductByCategory(
        categoryName: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductByCategory(categoryName, pageSize, pageNumber)
        }

    suspend fun getProductBySubCategory(
        subCategoryId: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductBySubCategory(subCategoryId, pageSize, pageNumber)
        }
}