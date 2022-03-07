package com.beltranbetania.postsjson.presentation.postDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.domain.model.Comment
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.model.User
import com.beltranbetania.postsjson.domain.usecase.GetCommentsUseCase
import com.beltranbetania.postsjson.domain.usecase.GetDetailUseCase
import com.beltranbetania.postsjson.domain.usecase.GetUserUseCase
import com.beltranbetania.postsjson.domain.usecase.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private var getDetailUseCase: GetDetailUseCase,
    private var getCommentsUseCase: GetCommentsUseCase,
    private var updateFavoriteUseCase: UpdateFavoriteUseCase,
    private var getUserUseCase: GetUserUseCase
) : ViewModel() {
    val commentModel = MutableLiveData<List<Comment>>()
    val userModel = MutableLiveData<User>()
    val postModel = MutableLiveData<Post>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDetailPost(postId: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDetailUseCase.invoke(postId)
            postModel.postValue(result!!)
            isLoading.postValue(false)
        }
    }

    fun updateFavorite(postId: Int, isFav:Boolean) {
        viewModelScope.launch {
            val result = updateFavoriteUseCase.invoke(isFav, postId)
            postModel.postValue(result!!)

        }
    }


}