package com.beltranbetania.postsjson.data.repository

import com.beltranbetania.postsjson.data.model.CommentModel
import com.beltranbetania.postsjson.data.model.UserModel
import com.beltranbetania.postsjson.data.network.PostService
import com.beltranbetania.postsjson.domain.model.Comment
import com.beltranbetania.postsjson.domain.model.User
import com.beltranbetania.postsjson.domain.model.toDomain
import javax.inject.Inject

class PostDetailRepository @Inject constructor(
    private val api: PostService
) {
    suspend fun getCommentsFromApi(id:Int): List<Comment> {
        val response: List<CommentModel> = api.getComments(id)
        return response.map { it.toDomain() }
    }

    suspend fun getUserFromApi(id:Int): User? {
        val response: UserModel? = api.getUser(id)
        return response?.toDomain()
    }

}