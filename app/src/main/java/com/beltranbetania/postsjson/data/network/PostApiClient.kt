package com.beltranbetania.postsjson.data.network
import com.beltranbetania.postsjson.data.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface PostApiClient {
    @GET("posts")
    suspend fun getAllPosts(): Response<List<PostModel>>
}