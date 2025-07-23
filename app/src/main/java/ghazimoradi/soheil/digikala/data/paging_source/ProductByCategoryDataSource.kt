package ghazimoradi.soheil.digikala.data.paging_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.repository.CategoryRepository

class ProductByCategoryDataSource(
    private val repository: CategoryRepository,
    val categoryId: String
) : PagingSource<Int, StoreProduct>() {

    override fun getRefreshKey(state: PagingState<Int, StoreProduct>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StoreProduct> {
        return try {
            val nextPageNumber = params.key ?: 1

            val containsNumber = categoryId.any { it.isDigit() }

            val response = if (containsNumber) {
                repository.getProductBySubCategory(
                    pageNumber = nextPageNumber.toString(),
                    pageSize = "5",
                    subCategoryId = categoryId
                ).data
            } else {
                repository.getProductByCategory(
                    pageNumber = nextPageNumber.toString(),
                    pageSize = "5",
                    categoryName = categoryId
                ).data
            }

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )

        } catch (e: Exception) {
            Log.d("3636", "error:$e ")
            LoadResult.Error(e)
        }
    }
}