package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostDetailRepository
import com.beltranbetania.postsjson.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: PostDetailRepository) {
    suspend operator fun invoke(postId: Int): User?{
        val user = repository.getUserFromApi(postId)
        return user
    }
}