package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class DeletePostUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(postId: Int):List<Post>{
        repository.deletePost(postId)
        return repository.getAllPostsFromDatabase()

    }
}