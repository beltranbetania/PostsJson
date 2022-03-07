package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(postId: Int):Post?{
        val quotes = repository.getPost(postId)
        if (!quotes.isNullOrEmpty()){
            return quotes[0]
        }
        return null
    }
}