package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.database.entities.toDatabase
import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class DeleteAllPostsUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke():List<Post>{
        var quotes = repository.clearPosts()
        return quotes
    }
}