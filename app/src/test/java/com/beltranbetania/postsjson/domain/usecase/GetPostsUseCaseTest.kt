package com.beltranbetania.postsjson.domain.usecase

import com.beltranbetania.postsjson.data.repository.PostRepository
import com.beltranbetania.postsjson.domain.model.Post
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPostsUseCaseTest{
    
    @RelaxedMockK
    private lateinit var postRepository: PostRepository

    lateinit var getPostsUseCase: GetPostsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getPostsUseCase = GetPostsUseCase(postRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { postRepository.getAllPostsFromApi() } returns emptyList()

        //When
        getPostsUseCase()

        //Then
        coVerify(exactly = 1) { postRepository.getAllPostsFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Post(1, 1, "Lorem Ipsum","body lorem ipsum", false))
        // coEvery is for coroutine else just every
        coEvery { postRepository.getAllPostsFromApi() } returns myList

        //When
        getPostsUseCase()

        //Then
        coVerify(exactly = 1) {
            postRepository.insertPosts(any())
            postRepository.getAllPostsFromDatabase()
            }
    }

}