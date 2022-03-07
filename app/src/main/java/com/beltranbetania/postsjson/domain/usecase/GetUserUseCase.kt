package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostDetailRepository
import com.beltranbetania.postsjson.domain.model.Comment
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: PostDetailRepository) {
    suspend operator fun invoke(postId: Int):List<Comment>{
        var quotes = repository.getCommentsFromApi(postId)
        return quotes
    }
}