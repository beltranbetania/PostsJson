package com.beltranbetania.postsjson.presentation.posts

import android.util.Log
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
    private val getQuotesUseCase: GetPostsUseCase/*,
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase*/
) : ViewModel() {

    val postModel = MutableLiveData<Post>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (!result.isNullOrEmpty()) {
                postModel.postValue(result[0])
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