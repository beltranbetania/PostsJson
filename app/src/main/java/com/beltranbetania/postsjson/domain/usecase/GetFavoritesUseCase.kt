package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val repository: PostRepository){
    suspend operator fun invoke():List<Post>{
        var posts = repository.getFavoritesFromDatabase()
        return posts
    }
}