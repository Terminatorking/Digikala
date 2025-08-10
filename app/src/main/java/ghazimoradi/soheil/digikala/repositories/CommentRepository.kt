package ghazimoradi.soheil.digikala.repositories

import ghazimoradi.soheil.digikala.data.models.productDetail.Comment
import ghazimoradi.soheil.digikala.data.models.productDetail.NewComment
import ghazimoradi.soheil.digikala.data.remote.BaseApiResponse
import ghazimoradi.soheil.digikala.data.remote.apiInterfaces.CommentApiInterface
import ghazimoradi.soheil.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val api: CommentApiInterface
) : BaseApiResponse() {

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }

    suspend fun getAllProductComments(
        id: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllProductComments(id, pageSize, pageNumber)
        }

    suspend fun getUserComments(
        token: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getUserComments(token, pageSize, pageNumber)
        }
}