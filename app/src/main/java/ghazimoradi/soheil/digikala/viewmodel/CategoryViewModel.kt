package ghazimoradi.soheil.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.model.category.SubCategory
import ghazimoradi.soheil.digikala.data.model.home.StoreProduct
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.data.source.ProductByCategoryDataSource
import ghazimoradi.soheil.digikala.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())

    var productByCategoryList: Flow<PagingData<StoreProduct>> =
        flow { emit(PagingData.Companion.empty()) }

    fun getAllDataFromServer() {
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
            ProductByCategoryDataSource(repository, categoryId)
        }.flow.cachedIn(viewModelScope)
    }
}