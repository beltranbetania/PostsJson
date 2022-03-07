package com.beltranbetania.postsjson.data.repository

import android.util.Log
import com.beltranbetania.postsjson.data.database.dao.PostDao
import com.beltranbetania.postsjson.data.database.entities.PostEntity
import com.beltranbetania.postsjson.data.model.CommentModel
import com.beltranbetania.postsjson.data.model.PostModel
import com.beltranbetania.postsjson.data.network.PostService
import com.beltranbetania.postsjson.domain.model.Comment
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.model.toDomain
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val api: PostService,
    private val postDao: PostDao
) {

    suspend fun getAllPostsFromApi(): List<Post> {
        val response: List<PostModel> = api.getPosts()
        return response.map { it.toDomain() }
    }

    suspend fun getAllPostsFromDatabase():List<Post>{
        val response: List<PostEntity> = postDao.getAllPosts()
        return response.map { it.toDomain() }
    }

    suspend fun insertPosts(quotes:List<PostEntity>){
        postDao.insertAll(quotes)
    }

    suspend fun clearPosts(){
        postDao.deleteAllPosts()
    }
}