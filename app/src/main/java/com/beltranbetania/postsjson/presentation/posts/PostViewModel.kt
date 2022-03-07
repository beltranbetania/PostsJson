package com.beltranbetania.postsjson.presentation.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.usecase.DeleteAllPostsUseCase
import com.beltranbetania.postsjson.domain.usecase.DeletePostUseCase
import com.beltranbetania.postsjson.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val deleteAllPostsUseCase: DeleteAllPostsUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {
    val postModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun loadPosts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPostsUseCase()
            isEmpty.postValue(result.isNullOrEmpty())
            if (!result.isNullOrEmpty()) {
                postModel.postValue(result)
                isLoading.postValue(false)
                isEmpty.postValue(false)
            }
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch {
            val result = deleteAllPostsUseCase()
            isEmpty.postValue(result.isNullOrEmpty())
            postModel.postValue(result)
        }
    }

    fun deletePost(id:Int) {
        viewModelScope.launch {
            val result =deletePostUseCase(id)
            isEmpty.postValue(result.isNullOrEmpty())
            postModel.postValue(result)
        }
    }

}