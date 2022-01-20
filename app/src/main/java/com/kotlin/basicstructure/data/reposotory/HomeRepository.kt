package com.kotlin.basicstructure.data.reposotory

import android.content.Context
import com.kotlin.basicstructure.data.model.UserResponse
import com.kotlin.basicstructure.data.source.network.ApiService
import com.kotlin.basicstructure.data.source.network.BaseApiResponse
import com.kotlin.basicstructure.data.source.network.NetworkResult
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepository @Inject constructor(
    private val apiService: ApiService, @ApplicationContext context: Context) : BaseApiResponse(context) {
    suspend fun getUsers(page: Int) : Flow<NetworkResult<UserResponse>> {
        return flow {
            emit(safeApiCall { apiService.getUsers(page) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUsers2(page : Int) : NetworkResult<UserResponse> {
        return safeApiCall { apiService.getUsers(page) }
    }

}