package com.kotlin.basicstructure.data.source.network

import com.kotlin.basicstructure.data.model.UserResponse
import com.kotlin.basicstructure.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.GET_USERS)
    suspend fun getUsers(@Query("page") page : Int): Response<UserResponse>

}