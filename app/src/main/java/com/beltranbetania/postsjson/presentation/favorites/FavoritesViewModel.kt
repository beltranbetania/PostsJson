package com.beltranbetania.postsjson.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beltranbetania.postsjson.domain.model.Post
import com.beltranbetania.postsjson.domain.usecase.DeleteAllPostsUseCase
import com.beltranbetania.postsjson.domain.usecase.DeletePostUseCase
import com.beltranbetania.postsjson.domain.usecase.GetFavoritesUseCase
import com.beltranbetania.postsjson.domain.usecase.GetPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    val postModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<Boolean>()

    fun loadFavorites() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getFavoritesUseCase()
            isEmpty.postValue(result.isNullOrEmpty())
            postModel.postValue(result)
            isLoading.postValue(false)
        }
    }



}