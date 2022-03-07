package com.beltranbetania.postsjson.data.network

import android.util.Log
import com.beltranbetania.postsjson.data.model.CommentModel
import com.beltranbetania.postsjson.data.model.PostModel
import com.beltranbetania.postsjson.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


class PostService @Inject constructor(private val api:PostApiClient) {

    suspend fun getPosts(): List<PostModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllPosts()
                response.body() ?: emptyList()
            }catch (e:Exception){
                emptyList()
            }

        }
    }

    suspend fun getComments(id:Int): List<CommentModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPostComments(id)
                response.body() ?: emptyList()
            }catch (e:Exception){
                emptyList()
            }
        }
    }

    suspend fun getUser(id:Int):UserModel? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPostUser(id)
                response.body() ?: null
            }catch (e:Exception){
                null
            }

        }
    }



}