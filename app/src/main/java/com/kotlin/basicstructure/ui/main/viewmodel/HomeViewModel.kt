package com.kotlin.basicstructure.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.basicstructure.data.model.UserResponse
import com.kotlin.basicstructure.data.reposotory.HomeRepository
import com.kotlin.basicstructure.data.source.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {

    var userLiveData: MutableLiveData<NetworkResult<UserResponse>> = MutableLiveData()
    private var page = 0

    fun updatePage() {
        page++
    }

    // with use of advanced Coroutines methods[Coroutines Flow - Collect].
    fun fetchUserData() {
        viewModelScope.launch {
            userLiveData.value = NetworkResult.Loading()
            homeRepository.getUsers(page).collect {
                userLiveData.value = it
            }
        }
    }

    // with use of normal Coroutines methods.
    fun fetchUserData1() {
        userLiveData.value = NetworkResult.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            userLiveData.postValue(homeRepository.getUsers2(page))
        }
    }
}