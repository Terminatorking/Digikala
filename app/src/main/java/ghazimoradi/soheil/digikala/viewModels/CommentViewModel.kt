package ghazimoradi.soheil.digikala.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ghazimoradi.soheil.digikala.data.models.productDetail.Comment
import ghazimoradi.soheil.digikala.data.models.productDetail.NewComment
import ghazimoradi.soheil.digikala.data.pagingSources.product.ProductCommentsPagingSource
import ghazimoradi.soheil.digikala.data.pagingSources.user.UserCommentsPagingSource
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import ghazimoradi.soheil.digikala.repositories.CommentRepository
import ghazimoradi.soheil.digikala.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val repository: CommentRepository
) : RemoteViewModel() {

    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    var productCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }

    var UserCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }

    fun getCommentList(productId: String) {
        productCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsPagingSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }

    fun getUserComments() {
        UserCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            UserCommentsPagingSource(repository, Constants.USER_TOKEN)
        }.flow.cachedIn(viewModelScope)
    }
}