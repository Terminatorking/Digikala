package ghazimoradi.soheil.digikala.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.models.category.SubCategory
import ghazimoradi.soheil.digikala.data.models.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.data.pagingSources.product.ProductByCategoryPagingSource
import ghazimoradi.soheil.digikala.repositories.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : RemoteViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())

    var productByCategoryList: Flow<PagingData<StoreProduct>> =
        flow {
            emit(
                PagingData.Companion.empty()
            )
        }

    override fun getAllDataFromServer() {
        viewModelScope.launch {
            launch {
                subCategory.emit(repository.getSubCategories())
            }
        }
    }

    fun getProductByCategory(categoryId: String) {
        productByCategoryList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductByCategoryPagingSource(repository, categoryId)
        }.flow.cachedIn(viewModelScope)
    }
}