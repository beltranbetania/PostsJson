package com.beltranbetania.postsjson.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase/*,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase*/
) : ViewModel() {

    val postModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadPosts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPostsUseCase()
            if (!result.isNullOrEmpty()) {
                postModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

 /*   fun randomQuote() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val quote = getRandomQuoteUseCase()
            if (quote != null) {
                quoteModel.value = quote
            }
            isLoading.postValue(false)
        }
    }*/
}