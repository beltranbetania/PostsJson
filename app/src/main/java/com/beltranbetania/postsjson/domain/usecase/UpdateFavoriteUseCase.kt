package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(isFav:Boolean, postId: Int): Post?{
        repository.updatePost(postId, isFav)
        val quotes = repository.getPost(postId)
        if (!quotes.isNullOrEmpty()){
            return quotes[0]
        }
        return null
    }
}