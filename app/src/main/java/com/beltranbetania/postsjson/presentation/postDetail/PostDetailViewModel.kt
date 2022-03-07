package com.beltranbetania.postsjson.presentation.postDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.domain.model.Comment
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.usecase.GetDetailUseCase
import com.beltranbetania.postsjson.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private var getDetailUseCase: GetDetailUseCase
) : ViewModel() {
    val commentModel = MutableLiveData<List<Comment>>()
    val isLoading = MutableLiveData<Boolean>()

    fun loadComments(postId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDetailUseCase.invoke(postId)
            if (!result.isNullOrEmpty()) {
                commentModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }


}