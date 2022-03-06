package com.beltranbetania.postsjson.data.network

import android.util.Log
import com.beltranbetania.postsjson.data.model.PostModel
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

}