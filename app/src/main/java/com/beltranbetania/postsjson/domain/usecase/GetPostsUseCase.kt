package com.beltranbetania.postsjson.domain.usecase

import android.util.Log
import com.beltranbetania.postsjson.data.database.entities.toDatabase
import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke():List<Post>{
        var quotes = repository.getAllPostsFromApi()
        return if(quotes.isNotEmpty()){
            repository.insertPosts(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getAllPostsFromDatabase()
        }
    }
}