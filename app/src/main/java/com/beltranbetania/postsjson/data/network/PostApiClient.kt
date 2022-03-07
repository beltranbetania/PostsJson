package com.beltranbetania.postsjson.data.network
import com.beltranbetania.postsjson.data.model.CommentModel
import com.beltranbetania.postsjson.data.model.PostModel
import com.beltranbetania.postsjson.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApiClient {
    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostModel>>

    @GET("comments")
    suspend fun getPostComments(@Query("postId") id: Int): Response<List<CommentModel>>

    @GET("users/{id}")
    suspend fun getPostUser(@Path("id") id: Int): Response<UserModel>
}