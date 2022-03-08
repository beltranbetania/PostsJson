package com.beltranbetania.postsjson.presentation.postDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.core.CoroutineContextProvider
import com.beltranbetania.postsjson.domain.model.Comment
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.model.User
import com.beltranbetania.postsjson.domain.usecase.GetCommentsUseCase
import com.beltranbetania.postsjson.domain.usecase.GetDetailUseCase
import com.beltranbetania.postsjson.domain.usecase.GetUserUseCase
import com.beltranbetania.postsjson.domain.usecase.UpdateFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private var getDetailUseCase: GetDetailUseCase,
    private var getCommentsUseCase: GetCommentsUseCase,
    private var updateFavoriteUseCase: UpdateFavoriteUseCase,
    private var getUserUseCase: GetUserUseCase
) : ViewModel() {
    val commentModel = MutableLiveData<List<Comment>>()
    val userModel = MutableLiveData<User>()
    val postModel = MutableLiveData<Post>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()
    val ioContext: CoroutineContext = (contextProvider.IO)
    fun getDetailPost(postId: Int) {
        viewModelScope.launch(ioContext) {
            isLoading.postValue(true)
            val tasks = listOf(
                async(ioContext) {
                    val result = getDetailUseCase.invoke(postId)
                    postModel.postValue(result!!)},
                async(ioContext) {
                    try {
                        val resultUser = getUserUseCase.invoke(postId)
                        userModel.postValue(resultUser!!)
                    }catch (e:Exception){
                        errorMsg.postValue("Error on loading more details, please check your conection")
                    }
                },
                async(ioContext) {
                    val resultComment = getCommentsUseCase.invoke(postId)
                    commentModel.postValue(resultComment)}
            )
            tasks.awaitAll()
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